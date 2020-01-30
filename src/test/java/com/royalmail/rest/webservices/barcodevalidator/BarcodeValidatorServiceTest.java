package com.royalmail.rest.webservices.barcodevalidator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BarcodeValidatorServiceTest {

	@Autowired
	private Service validationService;
	
    @Test
    public void testWithValidBarCode() {
        Assertions.assertTrue(validationService.validate("AB473124829GB"));
    }
     
    @Test
    public void testWithNullBarcode() {
    	Assertions.assertFalse(validationService.validate(null));
    }

    @Test
    public void testWithEmptyBarcode() {
    	Assertions.assertFalse(validationService.validate(null));
    }
    
    @Test
    public void testWithInvalidLengthBarCode() {
    	Assertions.assertFalse(validationService.validate("AB4GB"));
    }
    
    @Test
    public void testWithPrefixIsLowerBarCode() {
    	Assertions.assertFalse(validationService.validate("aB473124829GB"));
    }
    
    @Test
    public void testWithPrefixIsWrongBarCode() {
        Assertions.assertFalse(validationService.validate("9A473124829GB"));
    }
    
    @Test
    public void testWithCountryCodeIdNonGBBarCode() {
        Assertions.assertFalse(validationService.validate("AA473124829Gb"));
        Assertions.assertFalse(validationService.validate("AA473124829gb"));
    }

    @Test
    public void testWithCheckDegitInvalidBarCode() {
        Assertions.assertFalse(validationService.validate("9A473124829GB"));
    }

    /**
     * Test of the "step 4, if check digit = 11, change to check digit = 5"
     */    
    @Test
    public void testValidInputDigit_11_5() {
        assertTrue(validationService.validate("AA000000005GB"));
    }

    /**
     * Test of the "if check digit = 10, change to check digit = 0"
     */    
    @Test
    public void testValidInputDigit_10_0() {
        assertTrue(validationService.validate("AA022010000GB"));
    }
    
}
