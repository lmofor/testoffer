package com.poc.testoffert;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class TestoffertApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestoffertApplication.class, args);
	}

}
