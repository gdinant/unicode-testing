package com.gdinant.unicodedemo.controller;

import jakarta.validation.Valid;

import com.gdinant.unicodedemo.model.SaveMyEntityRequest;
import com.gdinant.unicodedemo.dao.model.MyEntity;
import com.gdinant.unicodedemo.service.MyEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/demo")
	public MyEntity save(@Valid @RequestBody SaveMyEntityRequest req) {
		return demoService.save(req.getValue());
	}

}
