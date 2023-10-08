package com.gdinant.unicodedemo.service;

import com.ibm.icu.text.BreakIterator;
import com.ibm.icu.text.Transliterator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Strings {

	/**
	 *
	 *
	 * Plain string, unicode unaware
	 *
	 *
	 */
	public String truncate(String s, int maxLength) {

		if (s.length() <= maxLength) {
			return s;
		}

		return s.substring(0, maxLength - 1);
	}











	/**
	 *
	 *
	 * Unicode aware - without normalization
	 *
	 *
	 */
	public String truncateUnicode(String s, int maxLength) {

		if (s.length() <= maxLength) {
			return s;
		}

		var boundary = BreakIterator.getCharacterInstance();
		boundary.setText(s);

		var end = boundary.last();
		var substringLength = s.length();
		while(substringLength > maxLength) {
			
			var start = boundary.previous();
			var substring = s.substring(start, end);
			substringLength -= substring.length();
			end = start;

		}

		return s.substring(0, end);
	}
















	/**
	 *
	 *
	 * Unicode aware - with normalization
	 *
	 *
	 */
	public String normalizedAndTruncateUnicode(String s, int maxLength) {

		if (s.length() <= maxLength) {
			return s;
		}

		var normalizer = Transliterator.getInstance("NFC");
		var normalized = normalizer.transform(s);
		if (normalized.length() <= maxLength) {
			return normalized;
		}

		var boundary = BreakIterator.getCharacterInstance();
		boundary.setText(s);

		var end = boundary.last();
		var untilTruncatedStringLengthIsBiggerThanMaxLength = s.length();
		while(untilTruncatedStringLengthIsBiggerThanMaxLength > maxLength) {
			var start = boundary.previous();
			var substring = s.substring(start, end);
			untilTruncatedStringLengthIsBiggerThanMaxLength -= substring.length();
			end = start;
		}

		return s.substring(0, end);
	}

}
