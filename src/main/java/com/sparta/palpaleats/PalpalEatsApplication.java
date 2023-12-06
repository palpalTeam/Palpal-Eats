package com.sparta.palpaleats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // Spring Security 인증 기능 제외
public class PalpalEatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PalpalEatsApplication.class, args);
	}

}
