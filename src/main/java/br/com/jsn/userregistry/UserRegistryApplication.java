package br.com.jsn.userregistry;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UserRegistryApplication {

	public static void main(String[] args) {
	SpringApplication.run(UserRegistryApplication.class, args);
	


	}

}


