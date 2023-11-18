package com.gdinant.unicodedemo.controller;

import jakarta.validation.Valid;

import com.gdinant.unicodedemo.model.Comment;
import com.gdinant.unicodedemo.model.SaveMyEntityRequest;
import com.gdinant.unicodedemo.dao.model.CommentEntity;
import com.gdinant.unicodedemo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/demo/{id}")
	public Comment find(@PathVariable Long id) {
		return commentService.find(id);
	}

	@PostMapping("/demo")
	@ResponseStatus(HttpStatus.CREATED)
	public Long save(@Valid @RequestBody SaveMyEntityRequest req) {
		return commentService.save(req.getValue());
	}

}
