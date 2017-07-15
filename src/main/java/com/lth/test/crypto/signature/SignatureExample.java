package com.lth.test.crypto.signature;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.lth.test.crypto.util.HexStringConverter;

public class SignatureExample {
	public static void main(String[] args) throws Exception {
		File publicKeyFile = new File("SignPublic.key");
		File privateKeyFile = new File("signPrivate.key");
		PublicKey publicKey = null;
		PrivateKey privateKey = null;
		if(publicKeyFile.exists() && privateKeyFile.exists()) {
			System.out.println("Key Files are exist.");
			
			byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyFile.getAbsolutePath()));
			byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyFile.getAbsolutePath()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
			
			System.out.println(HexStringConverter.convertToHexString(publicKey.getEncoded()));
			System.out.println(HexStringConverter.convertToHexString(privateKey.getEncoded()));
		} else {
			System.out.println("Generate new Key pair.");
			
			KeyPairGenerator generator =  KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair pair = generator.generateKeyPair();
			publicKey= pair.getPublic();
			privateKey = pair.getPrivate();
			Files.write(Paths.get(publicKeyFile.getAbsolutePath()), publicKey.getEncoded());
			Files.write(Paths.get(privateKeyFile.getAbsolutePath()), privateKey.getEncoded());
		}
		
		String plainText = "오늘도 별이 바람에 스치운다.";
		Charset charset = Charset.forName("UTF-8");
		byte[] signature = sign(privateKey, plainText.getBytes(charset));
		System.out.println(HexStringConverter.convertToHexString(signature));

		boolean verified = verify(publicKey, signature, plainText.getBytes(charset));
		System.out.println("verified = " + verified);
	}

	private static boolean verify(PublicKey publicKey, byte[] signatureData, byte[] bytes) throws Exception {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(bytes);
		boolean verified = signature.verify(signatureData);
		return verified;
	}

	private static byte[] sign(PrivateKey privateKey, byte[] bytes) throws Exception {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(bytes);
		byte[] signatureData = signature.sign();
		return signatureData;
	}
}
