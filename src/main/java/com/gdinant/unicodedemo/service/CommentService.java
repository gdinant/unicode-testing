package com.gdinant.unicodedemo.service;

import java.util.function.Function;

import com.gdinant.unicodedemo.dao.CommentRepository;
import com.gdinant.unicodedemo.dao.model.CommentEntity;
import com.gdinant.unicodedemo.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public Comment find(Long id) {
		return commentRepository.findById(id).map(toComment()).orElseThrow(() -> new RuntimeException("Entity not found"));
	}

	public Long save(String comment) {

		var truncatedComment = Strings.truncate(comment, CommentEntity.COMMENT_MAX_LENGTH);

		var entity = CommentEntity.builder().comment(truncatedComment).nComment(truncatedComment).build();

		return commentRepository.save(entity).getId();
	}

	private static Function<CommentEntity, Comment> toComment() {

		return c -> Comment.builder()
			.id(c.getId())
			.comment_varchar(c.getComment())
			.comment_nvarchar(c.getNComment())
			.build();
	}

}
