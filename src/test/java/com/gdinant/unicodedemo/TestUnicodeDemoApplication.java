package com.gdinant.unicodedemo;

import org.springframework.boot.SpringApplication;

public class TestUnicodeDemoApplication {

	public static void main(String[] args) {

		SpringApplication.from(UnicodeDemoApplication::main)
			.with(DemoContext.class)
			.run(args);
	}

}