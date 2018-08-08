/*
 * All Right Reserved message goes here
 */
package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.demo.services.TaxService;

/**
 * The purpose of this class is the provide tax calculation
 * function exposed to the client
 * 
 * @author Achille
 *
 */
@RestController
@RequestMapping("/api/tax")
public class DemoRestController {

	@Autowired
	private TaxService taxService;
	
	@RequestMapping(value = "calculate", 
			method = RequestMethod.POST, 
			produces = { MimeTypeUtils.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> calculate(
			@RequestBody String content) {
		try {
			String result = taxService.tax(content);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}		
	}
	
	
	
	
}
