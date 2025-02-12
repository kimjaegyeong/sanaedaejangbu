package com.e201;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class E201Application {

	public static void main(String[] args) {
		SpringApplication.run(E201Application.class, args);
	}

}
