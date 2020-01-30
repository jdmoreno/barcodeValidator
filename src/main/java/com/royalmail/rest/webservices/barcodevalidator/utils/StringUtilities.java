package com.royalmail.rest.webservices.barcodevalidator.utils;

import java.util.Random;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class StringUtilities {

	
    private static Random random = new Random();

	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXY";	
    private static final String NUMBERS = "0123456789";
    
	public static String generateNumericString(int length) {
	    return generateString(length, NUMBERS);
	}
	
	public static String generateAlphaString(int length) {
	    return generateString(length, ALPHABET);
	}	

	public static String generateAlphanumString(int length) {
	    return generateString(length, ALPHABET+NUMBERS);
	}	
	
	private static String generateString(int length, String seed) {
	    StringBuilder builder = new StringBuilder(length);

	    for (int i = 0; i < length; i++) {
	        builder.append(seed.charAt(random.nextInt(seed.length())));
	    }

	    return builder.toString();
	}
}