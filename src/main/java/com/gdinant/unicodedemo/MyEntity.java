package com.gdinant.unicodedemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UNICODE_TEST")
public class MyEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "N_COMMENT")
	private String nComment;

}
