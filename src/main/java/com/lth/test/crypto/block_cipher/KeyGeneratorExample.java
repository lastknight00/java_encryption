package com.lth.test.crypto.block_cipher;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class KeyGeneratorExample {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		
		SecureRandom random = new SecureRandom();
		byte[] keyData = new byte[16];
		random.nextBytes(keyData);
		
		SecretKey spec = new SecretKeySpec(keyData, "AES");
		
	}
}
