package com.lth.test.crypto.random;

import java.security.SecureRandom;

import com.lth.test.crypto.util.HexStringConverter;

public class SecureRandomExample {
	public static void main(String[] args) {
		SecureRandom sr = new SecureRandom();
		byte[] bytes = new byte[16];
		sr.nextBytes(bytes);
		System.out.println(HexStringConverter.convertToHexString(bytes));
	}
}
