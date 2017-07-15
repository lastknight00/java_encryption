package com.lth.test.crypto.block_cipher;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeySave {
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		
		byte[] keyData = key.getEncoded();
		
		System.out.println("Algorithm : " + key.getAlgorithm());
		System.out.println("Format : " + key.getFormat());
		
		File keyFile = new File("secretKey.raw");
		
		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(keyFile));){
			out.write(keyData);
		} catch (Exception e) {
		}
	}
}
