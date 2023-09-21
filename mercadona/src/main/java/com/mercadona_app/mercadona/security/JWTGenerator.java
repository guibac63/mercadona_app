package com.mercadona_app.mercadona.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {

  @Autowired
  private Environment env;

  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
    String token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, env.getProperty("jwt_secret"))
            .compact();
    
    return token;
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
                    .setSigningKey(env.getProperty("jwt_secret"))
                    .parseClaimsJws(token)
                    .getBody();
    return claims.getSubject();
  }

  public boolean validateToken(String token){
    try{
      Jwts.parser().setSigningKey(env.getProperty("jwt_secret")).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
        throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
    }
  }

}
