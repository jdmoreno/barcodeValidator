package com.royalmail.rest.webservices.barcodevalidator;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BarcodeValidatorAPITest {

	private static final String validBarcode = "AA473124829GB";
	private static final String invalidBarcode = "AA473124828GB";
	
	@Autowired
	private MockMvc mockMvc;

	@Mock
	Service barcodeValidatorService;

	@Test
	public void testSuccessWhenValidBarCode() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.get("/validate").param("barcode", validBarcode)
	    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("true"))
	    		.andReturn();
	}

	@Test
	public void testFailWhenInvalidBarCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validate").param("barcode", invalidBarcode)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("false"))
				.andReturn();
	}

	@Test
	public void testErrorWhenEmptyBarCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validate").param("barcode", "")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("false"))
				.andReturn();
	}

	@Test
	public void testFailWhenInvalidLengthBarCode() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/validate").param("barcode", "AA12GB")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string("false"))
				.andReturn();
	}
}