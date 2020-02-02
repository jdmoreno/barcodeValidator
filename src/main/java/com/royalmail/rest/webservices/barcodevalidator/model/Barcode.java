package com.royalmail.rest.webservices.barcodevalidator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.royalmail.rest.webservices.barcodevalidator.utils.StringUtilities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class Barcode {
	private static final int[] WEIGHTAGE = { 8, 6, 4, 2, 3, 5, 9, 7 };	
	private static final String BARCODEEX = "([A-Z]{2})([0-9]{8})([0-9]{1})(GB)";
	
	private String prefix;
	private String number;
	private String digit;
	private String countryCode;
	
	/**
	 * Transform an String to its corresponding barcode. Validates the barcode is correct.
	 * Returns null in case the String does not represent a valid barcode or if the string is null
	 * 
	 * @param sourceData
	 * @return
	 */
	
	public static Barcode fromString(final String sourceData) {
		if (StringUtils.isEmpty(sourceData)) {
			return null;
		}

		Barcode barcode = null;
		final Matcher matcher = Pattern.compile(BARCODEEX).matcher(sourceData);
		if (matcher.find()) {
			final String prefixTmp = matcher.group(1);
			final String numberTmp = matcher.group(2);
			final String digitTmp = matcher.group(3);
			final String countryCode = matcher.group(4);

			if (Integer.parseInt(digitTmp) == calculateCheckDigit(numberTmp)) {
				barcode = new Barcode(prefixTmp, numberTmp, digitTmp, countryCode);
			}
		}
		return barcode;
	}

	/**
	 * Generate a random barcode
	 * 
	 * @return
	 */
	public static Barcode generate() {
		final String prefixTmp = StringUtilities.generateAlphaString(2);
		final String numberTmp = StringUtilities.generateNumericString(8);
		final String digitTmp = Integer.toString(calculateCheckDigit(numberTmp));
		final String countryCode = "GB";
		return new Barcode(prefixTmp, numberTmp, digitTmp, countryCode);
	}

	private static int calculateCheckDigit(final String sourceData) {
		int sum = 0;
		for (int i = 0; i < WEIGHTAGE.length; i++) {			
			sum += (Character.getNumericValue(sourceData.charAt(i)) * WEIGHTAGE[i]);
		}
		int checkDigit = 11 - (sum % 11);

		switch (checkDigit) {
		
		// check digit = 10, change to check digit = 0
		case 10:
			checkDigit = 0;
			break;
			
		// if check digit = 11, change to check digit = 5
		case 11:
			checkDigit = 5;
			break;
			
		default:
			break;
		}
		return checkDigit;
	}
}
