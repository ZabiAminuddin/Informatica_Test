package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
	This is where the application starts
	@SpringBootApplication annotation will perform EnableAutoConfiguration, configuration and componentscan
 */
@SpringBootApplication
public class InformaticaTest {

	public static void main(String[] args) {
		SpringApplication.run(InformaticaTest.class, args);
	}

}
