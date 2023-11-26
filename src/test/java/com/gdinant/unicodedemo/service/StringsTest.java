package com.gdinant.unicodedemo.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		var unicode = "012345678\u0065\u03019";
		var maxLength = 10;

		// when
		var actual = Strings.truncateGraphemes(unicode, maxLength);

		// then
		assertThat(actual).isEqualTo("012345678\u0065\u0301");
	}

	@Test
	void countGrapheme() {
		// given
		var unicode = "012345678🤦‍♂️";

		// when
		var actual = Strings.countGraphemes(unicode);

		// then
		assertThat(actual).isEqualTo(10);
	}

	@Test
	void truncateGraphemes_MultiCodePoints() {
		// given
		// 🤦‍♂️ => U+1F926 U+200D U+2642 U+FE0F (4 code points) => 14 code points
		var unicode = "012345678🤦‍♂️9"; // (length: 15, graphemes: 11)
		var maxLength = 10;

		// when
		var actual = Strings.truncateGraphemes(unicode, maxLength);

		// then
		assertThat(actual).isEqualTo("012345678🤦‍♂️");
	}
}