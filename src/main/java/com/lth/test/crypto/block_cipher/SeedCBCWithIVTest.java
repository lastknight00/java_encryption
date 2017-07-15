package com.lth.test.crypto.block_cipher;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.lth.test.crypto.util.HexStringConverter;

public class SeedCBCWithIVTest {
	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance("SEED");
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		
		SecureRandom random = new SecureRandom();
		byte[] ivData = new byte[16];
		random.nextBytes(ivData);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivData);
		Charset charset = Charset.forName("UTF-8");
		
		String plainText = "오늘도 별이 바람에 스치운다.";
		byte[] encryptData = encrypt(key, plainText.getBytes(charset), ivParameterSpec);
		System.out.println(HexStringConverter.convertToHexString(encryptData));
		System.out.println(HexStringConverter.convertToString(encryptData));
		
		byte[] plainData = decrypt(key, ivParameterSpec, encryptData);
		System.out.println(HexStringConverter.convertToString(plainData, charset));
	}

	private static byte[] decrypt(SecretKey key, IvParameterSpec ivParameterSpec, byte[] encryptData) throws Exception {
		Cipher cipher = Cipher.getInstance("SEED/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
		byte[] decryptData = cipher.doFinal(encryptData);
		return decryptData;
	}

	private static byte[] encrypt(SecretKey key, byte[] bytes, IvParameterSpec ivParameterSpec) throws Exception {
		Cipher cipher = Cipher.getInstance("SEED/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
		byte[] encryptData = cipher.doFinal(bytes);
		return encryptData;
	}
}
