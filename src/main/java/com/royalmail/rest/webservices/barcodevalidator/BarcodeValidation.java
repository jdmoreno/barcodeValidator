package com.royalmail.rest.webservices.barcodevalidator;

import org.springframework.stereotype.Service;

import com.royalmail.rest.webservices.barcodevalidator.model.Barcode;

/**
 * This Service validates the given barcode to ensure that it adheres to the
 * business requirements. It validates the length, service indicator code,
 * checkdigit and the country code. It also calculates the checkdigit using the
 * input serial number to verify its value.
 *
 */
@Service
public class BarcodeValidation implements ValidationService {

	/**
	 * Returns
	 * - True if the barcode from the String parameter is a valid barcode.
	 * - False if the barcode from the String parameter is an invalid barcode. 
	 * 
	 * @param barcode
	 * @return boolean
	 * @throws Exception 
	 */
	public boolean validate(String barcode) {
		return (Barcode.fromString(barcode) != null);
	}
}
