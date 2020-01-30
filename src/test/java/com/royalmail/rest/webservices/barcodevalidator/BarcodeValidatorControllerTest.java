package com.royalmail.rest.webservices.barcodevalidator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BarcodeValidatorControllerTest {

	@Autowired
	private Controller barcodeValidatorController;

	/**
	 * Simple test to check if the context is loaded
	 */
	@Test
	public void contextLoads() {
		assertThat(barcodeValidatorController).isNotNull();
	}
		
	/***
	 * Test with valid barcode
	 */	
	@Test
	public void testValidBarcode() {
		Assertions.assertTrue(barcodeValidatorController.barcodeValidatorWithRequestParam("RM123456785GB"));
	}

	/***
	 * Test with invalid barcodes because service indicator not in capital letters 
	 */
	@Test
	public void testInvalidServiceIndicatorNotCapital() {
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("Aa473124829GB"));
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("zB473124829GB"));
	}

	/***
	 * Test with invalid barcodes because service indicator not letters 
	 */
	@Test
	public void testInvalidServiceIndicatorNotLetters() {
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("C1473124829GB"));
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("22473124829GB"));
	}
	
	@Test
	public void testInvalidCheckDigit() {
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("RM123456780GB"));
	}

	/***
	 * Test with country code not in capital letters
	 */
	@Test
	public void testInvalidCountryCodeLowerCase() {
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("AA473124828Gb"));
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("AA473124828gB"));
	}

	/***
	 * Test with country code not GB
	 */
	@Test
	public void testInvalidCountryCodeNotGB() {
		Assertions.assertFalse(barcodeValidatorController.barcodeValidatorWithRequestParam("AA473124828BB"));
	}	
}
