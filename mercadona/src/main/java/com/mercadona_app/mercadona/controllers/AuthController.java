package com.mercadona_app.mercadona.controllers;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona_app.mercadona.dto.AuthResponseDTO;
import com.mercadona_app.mercadona.dto.LoginDto;
import com.mercadona_app.mercadona.dto.RegisterDto;
import com.mercadona_app.mercadona.models.Role;
import com.mercadona_app.mercadona.models.UserEntity;
import com.mercadona_app.mercadona.repository.RoleRepository;
import com.mercadona_app.mercadona.repository.UserRepository;
import com.mercadona_app.mercadona.security.JWTGenerator;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;



@RestController
@RequestMapping("api/auth")
public class AuthController {

  private static final Logger logInfo = LoggerFactory.getLogger(AuthController.class);
  
  private AuthenticationManager authenticationManager;
  
  private UserRepository userRepository;
  
  private RoleRepository roleRepository;
  
  private PasswordEncoder passwordEncoder;

  private JWTGenerator jwtGenerator;
  
  @Autowired
  public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
      RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtGenerator = jwtGenerator;
  }

  @RateLimiter(name="rateLoginLimit", fallbackMethod = "loginFallbackMethod")
  @PostMapping("login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto ) {

    logInfo.info("login triggered");
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtGenerator.generateToken(authentication);
    logInfo.info("login success");
    return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
  }

  private void loginFallbackMethod() {
    logInfo.info("Too many login attemps");
  }




  @PostMapping("register")
  public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
    if(userRepository.existsByUsername(registerDto.getUsername())) {
      return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
    }

    UserEntity user = new UserEntity();
    user.setUsername(registerDto.getUsername());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Role roles = roleRepository.findByName("ADMIN").get();
    user.setRoles(Collections.singletonList(roles));

    userRepository.save(user);

    return new ResponseEntity<>("User registered success !",HttpStatus.OK);   
  }

}
