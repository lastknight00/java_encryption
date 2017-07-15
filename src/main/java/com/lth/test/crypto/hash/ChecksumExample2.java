package com.lth.test.crypto.hash;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import com.lth.test.crypto.util.HexStringConverter;

public class ChecksumExample2 {
	final static String FILENAME = "apache-tomcat-8.5.16.zip";
	public static void main(String[] args) throws Exception {
		File file = new File(FILENAME);
		String md5 = "7b00dce9d0e1ea2a6f13e35e1f134bda";
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		try (InputStream input = new DigestInputStream(new BufferedInputStream(new FileInputStream(file)), md);) {
			while(input.read() != -1);
		} catch (Exception e) {
		}
		byte[] hash = md.digest();
		System.out.println("MD5 : " + md5);
		System.out.println("HASH : " + HexStringConverter.convertToHexString(hash));
		System.out.println(md5.equalsIgnoreCase(HexStringConverter.convertToHexString(hash)));
	}
}
