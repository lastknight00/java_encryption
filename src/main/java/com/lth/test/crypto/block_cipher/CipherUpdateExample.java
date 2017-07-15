package com.lth.test.crypto.block_cipher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class CipherUpdateExample {
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
					OutputStream output = new BufferedOutputStream(new FileOutputStream(encryptFile));
					){
				int read = 0;
				byte[] inBuf = new byte[1024];
				byte[] outBuf = null;
				while((read = input.read(inBuf)) != -1) {
					outBuf = cipher.update(inBuf, 0, read);
					if(outBuf != null) {
						output.write(outBuf);
					}
				}
				outBuf = cipher.doFinal();
				output.write(outBuf);
			} catch (Exception e) {
			}
		}
		
		{
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(Cipher.DECRYPT_MODE, key);
			try (
					InputStream input = new BufferedInputStream(new FileInputStream(encryptFile));
					OutputStream output = new BufferedOutputStream(new FileOutputStream(decryptFile));
					){
				int read = 0;
				byte[] inBuf = new byte[1024];
				byte[] outBuf = null;
				while((read = input.read(inBuf)) != -1) {
					outBuf = cipher.update(inBuf, 0, read);
					if(outBuf != null) {
						output.write(outBuf);
					}
				}
				outBuf = cipher.doFinal();
				output.write(outBuf);
			} catch (Exception e) {
			}
		}
	}
}
