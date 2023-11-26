package com.gdinant.unicodedemo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@ToString
public class SaveMyEntityRequest {

	private String value;

}
