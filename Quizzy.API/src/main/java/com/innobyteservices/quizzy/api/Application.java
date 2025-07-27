package com.innobyteservices.quizzy.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Entry point for the Quizzy API Spring Boot application.
 * <p>
 * This class bootstraps the application using Spring Boot's {@link SpringApplication}.
 * It excludes {@link SecurityAutoConfiguration} to allow for custom security configuration.
 * </p>
 *
 * <p>
 * To start the application, simply run the {@code main} method.
 * </p>
 *
 */
@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class
})
public class Application {

	/**
	 * Main method used to launch the Spring Boot application.
	 *
	 * @param args command-line arguments passed during application startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
