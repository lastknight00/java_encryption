package com.lth.test.crypto.password_based_encryption;

import java.nio.charset.Charset;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.lth.test.crypto.util.HexStringConverter;

public class PBE2Example {
	public static void main(String[] args) throws Exception {
		char[] password = "mynameislee".toCharArray();
		
		String plainText = "오늘도 별이 바람에 스치운다.";
		Charset charset = Charset.forName("UTF-8");
		byte[] salt = new byte[8];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		int iterationCount = 1000;
		
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterationCount, 128);
		SecretKey key =  new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		byte[] encryptData = encrypt(key, plainText.getBytes(charset));
		System.out.println(HexStringConverter.convertToHexString(encryptData));
		
		byte[] plainData = decrypt(key, encryptData);
		System.out.println(HexStringConverter.convertToString(plainData, charset));
	}

	private static byte[] decrypt(SecretKey key, byte[] encryptData) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptData = cipher.doFinal(encryptData);
		return decryptData;
	}

	private static byte[] encrypt(SecretKey key, byte[] bytes) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptData = cipher.doFinal(bytes);
		return encryptData;
	}
}
