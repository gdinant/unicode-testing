package com.gdinant.unicodedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UnicodeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnicodeDemoApplication.class, args);
	}

}
