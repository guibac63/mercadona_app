package com.mercadona_app.mercadona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mercadona_app.mercadona.security.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class MercadonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadonaApplication.class, args);
	}

}
