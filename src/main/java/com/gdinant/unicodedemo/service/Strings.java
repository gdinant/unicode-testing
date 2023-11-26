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
	 * Unicode - Count graphemes
	 *
	 *
	 */
	public int countGraphemes(String s) {

		var it = BreakIterator.getCharacterInstance();
		it.setText(s);

		int graphemeCount = 0;
		while (it.next() != BreakIterator.DONE) {
			graphemeCount++;
		}

		return graphemeCount;
	}








	/**
	 *
	 *
	 * Unicode - Truncate graphemes
	 *
	 *
	 */
	public String truncateGraphemes(String s, int maxLength) {

		var length = countGraphemes(s);
		if (length <= maxLength) {
			return s;
		}

		var it = BreakIterator.getCharacterInstance();
		it.setText(s);
		it.last();

		for(int i = length; i > maxLength; i--) {
			it.previous();
		}

		return s.substring(0, it.current());
	}

}
