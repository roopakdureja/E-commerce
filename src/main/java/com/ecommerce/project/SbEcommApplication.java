package com.ecommerce.project;

import com.ecommerce.project.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SbEcommApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbEcommApplication.class, args);
		User user = new User();
	}

}
