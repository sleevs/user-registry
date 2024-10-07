package br.com.jsn.userregistry.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import org.springframework.stereotype.Component;

@Component
public class SHAEncryption {
    
    private static final String FIXED_SALT = "JSNSOFTWARE_SECURITY_2024";




	public String getSHA512SecurePassword(String senha) {
        String generatedPassword = null;
       
        try {
           
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(FIXED_SALT.getBytes());
            byte[] bytes = messageDigest.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
           
        }
        return null;
    }



	public boolean comparePassword(String senha, String storedPassword) {
        String generatedPassword = getSHA512SecurePassword(senha);
        return generatedPassword != null && generatedPassword.equals(storedPassword);
    }



    }


