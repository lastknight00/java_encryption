package com.lth.test.crypto.hash;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.lth.test.crypto.util.HexStringConverter;

public class MessageDigestExample {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Charset charset = Charset.forName("UTF-8");
		//String plainText = "오늘도 별이 바람에 스치운다.";
		String plainText = "12345";
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(plainText.getBytes(charset));
		byte[] hash = md.digest();
		System.out.println(HexStringConverter.convertToHexString(hash));
		
		
		md.reset();
		md.update("1".getBytes(charset));
		md.update("2".getBytes(charset));
		md.update("3".getBytes(charset));
		md.update("4".getBytes(charset));
		md.update("5".getBytes(charset));
		hash = md.digest();
		System.out.println(HexStringConverter.convertToHexString(hash));
		
		
		md.reset();
		hash = md.digest(plainText.getBytes(charset));
		System.out.println(HexStringConverter.convertToHexString(hash));
	}

}
