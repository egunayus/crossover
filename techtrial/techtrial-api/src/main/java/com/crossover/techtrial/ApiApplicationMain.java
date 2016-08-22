package com.crossover.techtrial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class to start Api application as a Spring Boot Application
 * 
 * @author egunay
 *
 */
@SpringBootApplication
public class ApiApplicationMain {

	public ApiApplicationMain() {
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplicationMain.class);
	}
	
}
