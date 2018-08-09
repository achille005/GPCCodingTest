package com.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.demo.controllers.DemoRestController;
import com.demo.services.TaxService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoRestController.class)
@WebAppConfiguration
public class DemoRestControllerTest {

	
	@MockBean
	private TaxService taxService;
	
	private String URL = "http://localhost:9596/api/tax/calculate";
	
	@Test
	public void case1() throws Exception {
		String request = "1 book at 12.49\\n1 music CD at 14.99\\n1 chocolate bar at 0.85";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);
		assertEquals(200, response.getStatusCodeValue());
		String expect = "1 book: 12.49";
		expect += "1 music CD: 16.49";
		expect += "1 chocolate bar: 0.85";
		expect += "Sales Taxes: 1.5";
		expect += "Total: 29.83";
		String output = response.getBody().replace("\n", "");
		assertEquals(expect, output);
	}
	
}