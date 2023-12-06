package com.sparta.palpaleats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PalpalEatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PalpalEatsApplication.class, args);
	}

}
