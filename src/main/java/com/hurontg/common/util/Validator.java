package com.hurontg.common.util;

public class Validator {
	public void validate(String value) {
		final char[] illegalChars = { '~', '&', '@', '#', '*', '%' };

		for (int i = 0; i < value.length(); i++) {
			for (int j = 0; j < illegalChars.length; j++) {
				if (value.charAt(i) == illegalChars[j]) {
					throw new IllegalArgumentException();
				}
			}
		}
	}
}
