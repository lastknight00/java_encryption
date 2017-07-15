package com.lth.test.crypto.password_based_encryption;

import java.nio.charset.Charset;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.lth.test.crypto.util.HexStringConverter;

public class PBEExample {
	public static void main(String[] args) throws Exception {
		char[] password = "".toCharArray();
		
		String plainText = "오늘도 별이 바람에 스치운다.";
		Charset charset = Charset.forName("UTF-8");
		
		byte[] salt = new byte[8];
		SecureRandom random = new SecureRandom();
		random.nextBytes(salt);
		int iterationCount = 1000;
		byte[] encryptData = encrypt(password, salt, iterationCount, plainText.getBytes(charset));
		System.out.println(HexStringConverter.convertToHexString(encryptData));
		
		byte[] plainData = decrypt(password, salt, iterationCount, encryptData);
		System.out.println(HexStringConverter.convertToString(plainData, charset));
	}

	private static byte[] decrypt(char[] password, byte[] salt, int iterationCount, byte[] encryptData) throws Exception {
		PBEKeySpec spec = new PBEKeySpec(password);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = factory.generateSecret(spec);
		PBEParameterSpec params = new PBEParameterSpec(salt, iterationCount);
		
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		cipher.init(Cipher.DECRYPT_MODE, key, params);
		byte[] plainData = cipher.doFinal(encryptData);
		return plainData;
	}

	private static byte[] encrypt(char[] password, byte[] salt, int iterationCount, byte[] bytes) throws Exception {
		PBEKeySpec spec = new PBEKeySpec(password);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		SecretKey key = factory.generateSecret(spec);
		PBEParameterSpec params = new PBEParameterSpec(salt, iterationCount);
		
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		cipher.init(Cipher.ENCRYPT_MODE, key, params);
		byte[] encryptData = cipher.doFinal(bytes);
		return encryptData;
	}
}
