package com.bmw.api;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypt {

	
	 private static final String ALGORITHM = "AES";
	 private static final String KEY = "1Hbfh667adfDEJ78";
	    
	 public static String encrypt(String value) throws Exception
	    {
	       /* Key key = generateKey();
	        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
	        String encryptedValue64 = Base64.getEncoder().encodeToString(encryptedByteValue.toString().getBytes("utf-8"));
	        String a=encryptedValue64.replace("/", "symbol");
	        System.out.println("in aes encrypt class"+a);*/
		 String asB64 = Base64.getEncoder().encodeToString(value.getBytes("utf-8"));

	        return asB64;
	               
	    }
	 
	 
	 public static String decrypt(String value) throws Exception
	    {
	        /*Key key = generateKey();
	        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte [] decryptedValue64 =  Base64.getDecoder().decode(value);
	        
	        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
	       
	        String decryptedValue = new String(decryptedByteValue,"utf-8");
	        System.out.println("RAV :"+decryptedValue);*/
		 byte[] asBytes = Base64.getDecoder().decode(value);
		   String decryptedValue = new String(asBytes,"utf-8");



	        return decryptedValue;
	                
	    }
	 
	 private static Key generateKey() throws Exception 
	    {
	        Key key = new SecretKeySpec(AESCrypt.KEY.getBytes(),AESCrypt.ALGORITHM);
	        return key;
	    }
	    
}
