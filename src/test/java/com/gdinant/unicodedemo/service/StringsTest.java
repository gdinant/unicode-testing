package com.gdinant.unicodedemo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class StringsTest {

	@Test
	void test() {

		var s1 = "Hé";
		display(s1);

		var s2 = "\u0048\u0065\u0301";
		display(s2);

		System.out.println("%s equals %s: %s".formatted(s1, s2, s1.equals(s2)));
	}

	private static void display(String x) {
		var cp = x.codePoints().mapToObj(Integer::toHexString).toList();
		System.out.printf("%s (0x%s): %s chars%n", x, cp, x.length());
	}

	@Test
	void truncate_Empty() {
		// given
		var unicode = "";
		var maxLength = 10;

		// when
		var actual = Strings.truncate(unicode, maxLength);

		// then
		assertThat(actual.length()).isLessThanOrEqualTo(maxLength);
		assertThat(actual).isEmpty();
	}

	@Test
	void truncate_Lowerbound() {
		// given
		var unicode = "0123";
		var maxLength = 10;

		// when
		var actual = Strings.truncate(unicode, maxLength);

		// then
		assertThat(actual.length()).isLessThanOrEqualTo(maxLength);
		assertThat(actual).isEqualTo(unicode);
	}

	@Test
	void truncate_Upperbound() {
		// given
		var unicode = "0123456🤦‍♂️";
		var maxLength = 10;

		// when
		var actual = Strings.truncate(unicode, maxLength);

		// then
		assertThat(actual.length()).isLessThanOrEqualTo(maxLength);
		assertThat(actual).isEqualTo("0123456\uD83E\uDD26");
	}

	@Test
	void truncate_Limit() {
		// given
		var unicode = "0123456789";
		var maxLength = 10;

		// when
		var actual = Strings.truncate(unicode, maxLength);

		// then
		assertThat(actual.length()).isLessThanOrEqualTo(maxLength);
		assertThat(actual).isEqualTo(unicode);
	}

	@Test
	void truncateUnicode2() {

		var unicode = "012345678🤦🏼";
		var maxLength = 10;

		unicode.substring(0, maxLength - 1);

	}

	@Test
	void truncateUnicode() {
		// given
		var unicode = "012345678\u0065\u0301";
		var maxLength = 10;

		// when
		var actual = Strings.truncateUnicode(unicode, maxLength);

		// then
		assertThat(actual.length()).isLessThanOrEqualTo(maxLength);
		assertThat(actual).isEqualTo("012345678");
	}

	@Test
	void truncateUnicode_MultiCodePoints() {
		// given
		var unicode = "012345678🤦🏼";
		var maxLength = 10;

		// when
		var actual = Strings.truncateUnicode(unicode, maxLength);

		// then
		assertThat(actual.length()).isLessThanOrEqualTo(maxLength);
		assertThat(actual).isEqualTo("012345678");
	}
}