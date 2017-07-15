package com.lth.test.crypto.message_authentication_code;

import java.nio.charset.Charset;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import com.lth.test.crypto.util.HexStringConverter;

public class HMACExample {
	public static void main(String[] args) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
		SecretKey key = keyGenerator.generateKey();
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(key);
		Charset charset = Charset.forName("UTF-8");
		String plainText = "오늘도 별이 바람에 스치운다.";
		byte[] macData = mac.doFinal(plainText.getBytes(charset));
		System.out.println(HexStringConverter.convertToHexString(macData));
	}
}
