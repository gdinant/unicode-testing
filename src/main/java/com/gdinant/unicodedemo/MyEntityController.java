package com.gdinant.unicodedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyEntityController {

	private final MyEntityService demoService;

	@GetMapping("/demo/{id}")
	public MyEntity find(@PathVariable Long id) {
		return demoService.find(id);
	}

	@PutMapping("/demo")
	public MyEntity save(@RequestBody String value) {
		return demoService.save(value);
	}

}
