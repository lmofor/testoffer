package com.poc.testoffert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TestoffertApplication {

	private static final Logger log = LoggerFactory.getLogger(TestoffertApplication.class);

	private final Environment env;

	public TestoffertApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		//SpringApplication.run(TestoffertApplication.class, args);
		SpringApplication app = new SpringApplication(TestoffertApplication.class);
		Map<String, Object> defProperties = new HashMap();
		defProperties.put("spring.profiles.default", "dev");
		app.setDefaultProperties(defProperties);
		Environment env = app.run(args).getEnvironment();


	}

}
