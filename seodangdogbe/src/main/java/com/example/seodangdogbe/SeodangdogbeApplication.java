package com.example.seodangdogbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing // Auditing 주입
@SpringBootApplication
public class SeodangdogbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeodangdogbeApplication.class, args);
	}

}
