package com.mercadona_app.mercadona.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DTOTester {
  
  @Test
  void testAllDTOs(){
  
    BeanTester beanTester = new BeanTester();
    beanTester.testBean(AuthResponseDTO.class);
    beanTester.testBean(LoginDto.class);
    beanTester.testBean(RegisterDto.class);

  }

  @Test
  public void testDefaultConstructor() {
      AuthResponseDTO authResponseDTO = new AuthResponseDTO();

      assertNull(authResponseDTO.getAccessToken());
      assertEquals("Bearer ", authResponseDTO.getTokenType());
  }

  @Test
  public void testParameterizedConstructor() {
      String accessToken = "myAccessToken";
      AuthResponseDTO authResponseDTO = new AuthResponseDTO(accessToken);

      assertEquals(accessToken, authResponseDTO.getAccessToken());
      assertEquals("Bearer ", authResponseDTO.getTokenType());
  }

}
