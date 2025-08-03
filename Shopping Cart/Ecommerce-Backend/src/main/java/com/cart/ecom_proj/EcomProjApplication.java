package com.cart.ecom_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class EcomProjApplication {

	public static void main(String[] args) {
		// Load .env variables (only works locally or if .env is present)
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// Convert DATABASE_URL (Heroku-style) to JDBC format
		String rawUrl = dotenv.get("DATABASE_URL");
		if (rawUrl != null && rawUrl.startsWith("postgres://")) {
			String jdbcUrl = rawUrl.replace("postgres://", "jdbc:postgresql://");
			System.setProperty("spring.datasource.url", jdbcUrl);
		}

		// Set all other environment variables as system properties
		dotenv.entries().forEach(entry -> {
			// Avoid overriding spring.datasource.url if already set above
			if (!entry.getKey().equals("DATABASE_URL")) {
				System.setProperty(entry.getKey(), entry.getValue());
			}
		});

		SpringApplication.run(EcomProjApplication.class, args);
	}
}
