package com.gdinant.unicodedemo.model;

import jakarta.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@ToString
public class SaveMyEntityRequest {

	@Size(max = 10)
	private String value;

}
