package com.amacodecode.util;

import org.apache.commons.lang.RandomStringUtils;

public class Base62 {

	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static final int BASE = ALPHABET.length();

	private Base62() {}
	
	public static String generateRandomId(){
		return RandomStringUtils.randomAlphanumeric(6);
	
	}
	public static String generateRandomTransactPin(){
		return RandomStringUtils.randomNumeric(4);
	
	}
	public static String generateRandomPasswordRequestPin(){
		return RandomStringUtils.randomAlphanumeric(10);
	
	}
	public static String generateRandomAlphanumeric(){
		return RandomStringUtils.randomAlphanumeric(6);
	
	}
	public static String fromBase10(int i) {
		StringBuilder sb = new StringBuilder("");
		while (i > 0) {
			i = fromBase10(i, sb);
		}
		return sb.reverse().toString();
	}

	private static int fromBase10(int i, final StringBuilder sb) {
		int rem = i % BASE;
		sb.append(ALPHABET.charAt(rem));
		return i / BASE;
	}

	public static int toBase10(String str) {
		return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
	}

	private static int toBase10(char[] chars) {
		int n = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			n += toBase10(ALPHABET.indexOf(chars[i]), i);
		}
		return n;
	}

	private static int toBase10(int n, int pow) {
		return n * (int) Math.pow(BASE, pow);
	}
}