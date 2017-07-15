package com.lth.test.crypto;

import java.security.Provider;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Hello world!
 *
 */
public class Test {
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());

		Provider provider = Security.getProvider("BC");
		if (provider != null) {
			System.out.println("Bouncy Castle provider is available");
			System.out.println(provider.getInfo());
		} else {
			System.out.println("Bouncy Castle provider is NOT available");
		}
	}
}
