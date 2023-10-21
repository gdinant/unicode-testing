package com.gdinant.unicodedemo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.apache.commons.lang3.StringEscapeUtils;

class StringsTest {

	@Test
	void multiPointEquality() {
		// given
		var eAccuteSingleCodePoint = "é";
		var eAccuteMultipleCodePoint = "\u0065\u0301";

		// when
		var equals = eAccuteSingleCodePoint.equals(eAccuteMultipleCodePoint);

		// then
		System.out.println("%s equals %s: %s".formatted(eAccuteSingleCodePoint, eAccuteMultipleCodePoint, equals));
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
	void truncateUnicode_NormalizeFirst_NoTruncation() {
		// given
		var unicode = "012345678\u0065\u0301";
		var maxLength = 10;

		// when
		var actual = Strings.normalizedAndTruncateUnicode(unicode, maxLength);

		// then
		assertThat(actual.length()).isLessThanOrEqualTo(maxLength);
		assertThat(actual).isEqualTo("012345678é");
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