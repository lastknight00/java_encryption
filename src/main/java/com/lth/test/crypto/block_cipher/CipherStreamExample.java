package com.lth.test.crypto.block_cipher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class CipherStreamExample {
	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		String transformation = "AES/ECB/PKCS5Padding";
		File plainFile = new File("plain.rtf");
		File encryptFile = new File("encrypt.rtf");
		File decryptFile = new File("decrypt.rtf");
		
		{
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			try (
					InputStream input = new BufferedInputStream(new FileInputStream(plainFile));
					OutputStream output = new CipherOutputStream(new BufferedOutputStream(new FileOutputStream(encryptFile)), cipher);
					){
				int read = 0;
				byte[] buffer = new byte[1024];
				while((read = input.read(buffer)) != -1) {
					output.write(buffer, 0, read);
				}
			} catch (Exception e) {
			}
		}
		
		{
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.DECRYPT_MODE, key);
			try (
					InputStream input = new CipherInputStream(new BufferedInputStream(new FileInputStream(encryptFile)), cipher);
					OutputStream output = new BufferedOutputStream(new FileOutputStream(decryptFile));
					){
				int read = 0;
				byte[] buffer = new byte[1024];
				while((read = input.read(buffer)) != -1) {
					output.write(buffer, 0, read);
				}
			} catch (Exception e) {
			}
		}
	}
}
