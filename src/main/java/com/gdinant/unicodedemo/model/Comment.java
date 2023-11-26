package com.gdinant.unicodedemo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Comment {

	private Long id;

	private String comment_varchar;
	private String comment_nvarchar;

}
