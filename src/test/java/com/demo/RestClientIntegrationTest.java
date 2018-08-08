/**
 * 
 */
package com.demo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.demo.entities.Item;



/**
 * The purpose of this class is the demonstrate 1 approach of 
 * doing integration test
 * 
 * @author Achille
 *
 *
 */
public class RestClientIntegrationTest extends Assert {
	
	RestTemplate template = new RestTemplate();
	private static final String BASE_URL = "http://localhost:9596/";

	@Test
	public void testTax1(){
		RestTemplate template = new RestTemplate();
		assertNotNull(template.getForObject(BASE_URL+"api/tax/tax1", String.class));
	}
	@Test
	public void testTax2(){
		RestTemplate template = new RestTemplate();
		assertNotNull(template.getForObject(BASE_URL+"api/tax/tax2", String.class));
	}
	
	@Test
	public void testTax3(){
		RestTemplate template = new RestTemplate();
		assertNotNull(template.getForObject(BASE_URL+"api/tax/tax2", String.class));
	}
	
	//Failure cases but pass them because of (expected = HttpClientErrorException.class)
	
	@Test(expected = HttpClientErrorException.class)
	public void testTax1_failed(){
		RestTemplate template = new RestTemplate();
		ResponseEntity<Item> item = template.getForEntity(BASE_URL+"api/tax/tax1_BAD", Item.class);
	}
	@Test(expected = HttpClientErrorException.class)
	public void testTax2_failed(){
		RestTemplate template = new RestTemplate();
		ResponseEntity<Item> item = template.getForEntity(BASE_URL+"api/tax/tax2_BAD", Item.class);
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void testTax3_failed(){
		RestTemplate template = new RestTemplate();
		ResponseEntity<Item> item = template.getForEntity(BASE_URL+"api/tax/tax3_BAD", Item.class);
	}
}
