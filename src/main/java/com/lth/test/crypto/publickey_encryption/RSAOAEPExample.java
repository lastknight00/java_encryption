package com.lth.test.crypto.publickey_encryption;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.lth.test.crypto.util.HexStringConverter;

public class RSAOAEPExample {
	public static void main(String[] args) throws Exception {
		File publicKeyFile = new File("public1.key");
		File privateKeyFile = new File("private1s.key");
		PublicKey publicKey = null;
		PrivateKey privateKey = null;
		if(publicKeyFile.exists() && privateKeyFile.exists()) {
			byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyFile.getAbsolutePath()));
			byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile.getAbsolutePath()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
		} else {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair pair = generator.generateKeyPair();
			publicKey = pair.getPublic();
			privateKey = pair.getPrivate();
			Files.write(Paths.get(publicKeyFile.getAbsolutePath()), publicKey.getEncoded());
			Files.write(Paths.get(privateKeyFile.getAbsolutePath()), privateKey.getEncoded());
		}
		
		String plainText = "오늘도 별이 바람에 스치운다.";
		Charset charset = Charset.forName("UTF-8");
		byte[] encryptData = encrypt(publicKey, plainText.getBytes(charset));
		System.out.println(HexStringConverter.convertToHexString(encryptData));
		
		byte[] decryptData = decrypt(privateKey, encryptData);
		System.out.println(HexStringConverter.convertToString(decryptData, charset));
	}

	private static byte[] decrypt(PrivateKey privateKey, byte[] encryptData) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptData = cipher.doFinal(encryptData);
		return decryptData;
	}

	private static byte[] encrypt(PublicKey publicKey, byte[] bytes) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptData = cipher.doFinal(bytes);
		return encryptData;
	}
}
