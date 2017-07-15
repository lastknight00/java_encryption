package com.lth.test.crypto.block_cipher;

import java.nio.charset.Charset;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.lth.test.crypto.util.HexStringConverter;

public class AESCTRWithIVTest {
	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator  = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		
		SecureRandom random = new SecureRandom();
		byte[] ivData = new byte[16];
		random.nextBytes(ivData);
		IvParameterSpec spec = new IvParameterSpec(ivData);
		Charset charset = Charset.forName("UTF-8");
		
		String plainText = "오늘도 별이 바람에 스치운다.";
		byte[] encryptData = encrypt(key, plainText.getBytes(charset), spec);
		System.out.println(HexStringConverter.convertToHexString(encryptData));
		System.out.println(HexStringConverter.convertToString(encryptData));
		
		byte[] decryptData = decrypt(key, encryptData, spec);
		System.out.println(HexStringConverter.convertToString(decryptData, charset));
	}

	private static byte[] decrypt(SecretKey key, byte[] bytes, IvParameterSpec spec) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		byte[] decryptData = cipher.doFinal(bytes);
		return decryptData;
	}

	private static byte[] encrypt(SecretKey key, byte[] bytes, IvParameterSpec spec) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);
		byte[] encryptData = cipher.doFinal(bytes);
		return encryptData;
	}
}
