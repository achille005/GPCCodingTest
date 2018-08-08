package com.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.controllers.DemoRestController;
import com.demo.services.TaxService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DemoRestController.class, secure = false)
public class DemoRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaxService taxService;
	
	@Test
	public void test() throws Exception {
		String content = "1 book at 12.49\\n1 music CD at 14.99\\n1 chocolate bar at 0.85";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("http://localhost:9596/api/tax/calculate")
				.accept(MediaType.TEXT_PLAIN)
				.content(content)
				.contentType(MediaType.TEXT_PLAIN);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(200, response.getStatus());
		String output = "1 book : 12.49";
		output += "1 music CD: 16.49";
		output += "1 chocolate bar: 0.85";
		output += "Sales Taxes: 1.50";
		output += "Total: 29.83";
		assertEquals(output, response.getContentAsString());
	}
	
}
