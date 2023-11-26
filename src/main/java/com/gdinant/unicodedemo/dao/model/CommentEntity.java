package com.gdinant.unicodedemo.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unicode_test")
public class CommentEntity {

	// !#of characters / graphemes but memory (byte-pairs)!
	public static final int COMMENT_MAX_LENGTH = 10;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "comment", length = COMMENT_MAX_LENGTH)
	private String comment;

	@Nationalized
	@Column(name = "n_comment", length = COMMENT_MAX_LENGTH)
	private String nComment;

}
