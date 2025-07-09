package com.potodev.NapRoute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.potodev.NapRoute")
@EnableJpaRepositories(basePackages = "com.potodev.NapRoute.repository")
@EntityScan(basePackages = "com.potodev.NapRoute.model")
public class NapRouteApplication {

	public static void main(String[] args) {
		SpringApplication.run(NapRouteApplication.class, args);
	}

}
