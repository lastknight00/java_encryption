package com.lth.test.crypto.hash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.lth.test.crypto.util.HexStringConverter;

public class ChecksumExample {
	final static String FILENAME = "apache-tomcat-8.5.16.zip";
	
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
		File file = new File(FILENAME);
		System.out.println(file.exists());
		String md5 = "7b00dce9d0e1ea2a6f13e35e1f134bda";
		MessageDigest md = MessageDigest.getInstance("MD5");
		try(InputStream input = new BufferedInputStream(new FileInputStream(file));) {
			byte[] buffer = new byte[1024];
			int read = -1;
			while((read = input.read(buffer)) != -1) {
				md.update(buffer, 0, read);
			}
		} catch (Exception e) {
		}
		byte[] hash = md.digest();
		System.out.println("MD5 : " + md5);
		System.out.println("Hash : " + HexStringConverter.convertToHexString(hash));
		System.out.println(md5.equalsIgnoreCase(HexStringConverter.convertToHexString(hash)));
	}
}
