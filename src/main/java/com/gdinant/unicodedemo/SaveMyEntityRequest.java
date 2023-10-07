package com.gdinant.unicodedemo;

import jakarta.validation.constraints.Size;

import lombok.Builder;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@ToString
public class SaveMyEntityRequest {

	@Size(max = 5)
	private String value;

}
