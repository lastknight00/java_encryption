package com.lth.test.crypto.block_cipher;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class KeySpecExample {
	public static void main(String[] args) throws Exception {
		byte[] desKeyData = {(byte)0x01, (byte)0x09, (byte)0x07, (byte)0x07, (byte)0x01, (byte)0x02, (byte)0x02, (byte)0x08};
		
		DESKeySpec descKeySpec = new DESKeySpec(desKeyData);
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(descKeySpec);
	}
}
