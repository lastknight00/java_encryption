package com.lth.test.crypto.util;

import java.nio.charset.Charset;

public class HexStringConverter {
	static public String convertToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (final byte b : bytes) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
	
	static public String convertToString(byte[] bytes) {
		return new String(bytes);
	}
	
	static public String convertToString(byte[] bytes, Charset charset) {
		return new String(bytes, charset);
	}
}
