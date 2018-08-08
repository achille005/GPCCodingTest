/*
 * All Right Reserved message goes here
 */
package com.demo.services;

import java.io.BufferedReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.demo.entities.Item;

@Service("taxService")
public class TaxServiceImpl implements TaxService {

	@Override
	public String tax(String content) {
		List<Item> items = new ArrayList<Item>();
		new BufferedReader(new StringReader(content.trim())).lines().forEach(
			(line) -> {
				items.add(parseStringToItem(line));
			}
	    );		
		SalesTax salesTax = new SalesTax();
		String result = "";
		BigDecimal saleTaxes = BigDecimal.valueOf(0);
		BigDecimal total = BigDecimal.valueOf(0);
		for(Item item : items) {
			salesTax.calculateTax(item);
			result += item.getName() + ": " + item.getCost().add(item.getSalesTax()).doubleValue() + "\n";
			saleTaxes = saleTaxes.add(item.getSalesTax());
			total = total.add(item.getCost().add(item.getSalesTax()));
		}
		result += "Sales Taxes: " + saleTaxes.doubleValue() + "\n";
		result += "Total: " + total.doubleValue() + "\n";
		return result;
	}
	
	private Item parseStringToItem(String s) {
		String []result = s.trim().split(" at ");
		Item item = new Item(result[0].trim());
		item.setCost(BigDecimal.valueOf(Double.parseDouble(result[1].trim())));
		item.setItemType(getItemTypeFromString(result[0].trim()));
		return item;
	}
	
	private String getItemTypeFromString(String s) {
		if(s.toLowerCase().contains("book")) {
			return "book";
		} else if(s.toLowerCase().contains("chocolate") && !s.toLowerCase().contains("imported") ) {
			return "food";
		} else if(s.toLowerCase().contains("headache pills") && !s.toLowerCase().contains("imported")) {
			return "medical";
		} else if(s.toLowerCase().contains("imported")) {
			return "imported";
		}
		return "";
	}

}
