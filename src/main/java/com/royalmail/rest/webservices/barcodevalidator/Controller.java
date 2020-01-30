package com.royalmail.rest.webservices.barcodevalidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private Service barcodeValidatorService;

	/**
	 * This method gets the input barcode from the request parameter & invokes the
	 * service method to validate the barcode
	 * 
	 * @RequestParam String
	 * @return boolean
	 */
	@GetMapping("/validate")
	public boolean barcodeValidatorWithRequestParam(
			@RequestParam(name = "barcode", required = false, defaultValue = "") String barcode) {
		logger.info("Request received barcode {}", barcode);
		return barcodeValidatorService.validate(barcode);
	}
}
