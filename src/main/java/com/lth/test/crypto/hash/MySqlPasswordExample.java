package com.lth.test.crypto.hash;

import java.security.MessageDigest;

import com.lth.test.crypto.util.HexStringConverter;

public class MySqlPasswordExample {
	public static void main(String[] args) {
		String password = "helloworld";
		String digest = password(password);
		System.out.println("MySQL Password = " + digest);
	}
	
	public static byte[] getHash(byte[] input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			return md.digest(input);
		} catch (Exception e) {
			throw new RuntimeException("SHA1 Algorithm Not Found", e);
		}
	}
	public static String password(byte[] input) {
		byte[] digest = null;
		digest = getHash(input);
		digest = getHash(digest);
		StringBuilder sb = new StringBuilder(1 + digest.length);
		sb.append("*");
		sb.append(HexStringConverter.convertToHexString(digest).toUpperCase());
		return sb.toString();
	}
	
	public static String password(String input) {
		if(input == null) {
			return null;
		}
		return password(input.getBytes());
	}
}
