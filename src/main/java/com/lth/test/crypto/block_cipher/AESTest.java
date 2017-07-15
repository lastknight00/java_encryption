package com.lth.test.crypto.block_cipher;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.lth.test.crypto.util.HexStringConverter;

public class AESTest {

	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		Charset charset = Charset.forName("UTF-8");
		
		String plainText = "오늘도 별이 바람에 스치운다.";
		byte[] encryptData = encrypt(key, plainText.getBytes(charset));
		
		System.out.println(HexStringConverter.convertToHexString(encryptData));
		System.out.println(HexStringConverter.convertToString(encryptData));
		
		byte[] plainData = decrypt(key, encryptData);
		System.out.println(HexStringConverter.convertToString(plainData));
	}

	private static byte[] encrypt(SecretKey key, byte[] plainText) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		byte[] encryptData = cipher.doFinal(plainText);
		return encryptData;
	}

	private static byte[] decrypt(SecretKey key, byte[] encryptData) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptData = cipher.doFinal(encryptData);
		return decryptData;
	}
}
