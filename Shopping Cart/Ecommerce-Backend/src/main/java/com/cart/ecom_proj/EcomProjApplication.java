package com.cart.ecom_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class EcomProjApplication {

	public static void main(String[] args) {
		// Load environment variables from .env if present (for local dev only)
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		dotenv.entries().forEach(entry -> {
		 System.setProperty(entry.getKey(), entry.getValue());
		});

		SpringApplication.run(EcomProjApplication.class, args);
	}
}
