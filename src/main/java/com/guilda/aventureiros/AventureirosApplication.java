package com.guilda.aventureiros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AventureirosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AventureirosApplication.class, args);
	}
}
