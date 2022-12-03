package com.ideas2It;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

@SpringBootApplication
public class VehicleApplication {
	public static void main(String[] args) {
		SpringApplication.run(VehicleApplication.class, args);
	}
}
