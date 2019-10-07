package com.minorjava.week3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Week3Application {

	public static void main(String[] args) {
		SpringApplication.run(Week3Application.class, args);
	}

}
