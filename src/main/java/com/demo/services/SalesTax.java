/*
 * All Right Reserved message goes here
 */
package com.demo.services;

import java.math.BigDecimal;
import com.demo.entities.Item;
/**
 * The purpose of this class is to tax calculation methods 
 * 
 * @author Achille
 *
 */
public class SalesTax {

	private static final BigDecimal ROUND_FACTOR = new BigDecimal("0.05");
	private static final BigDecimal IMPORTED_ITEMS_TAX_PERCENTAGE = new BigDecimal("5");
	private static final BigDecimal DOMESTIC_TAX_PERCENTAGE = new BigDecimal("10");

	private static BigDecimal taxPercentage = null;


	/**
	 * Calculate Sales Tax for an item.
	 * 
	 * @param item
	 *            item for sales.
	 */
	private void calculateSalesTax(Item item) {
		BigDecimal salesTax = item.getCost().multiply(taxPercentage).divide(new BigDecimal("100"));
		salesTax = roundOff(salesTax);
		item.setSalesTax(salesTax);
	}

	/**
	 * round up.
	 * 
	 * @param value to be rounded
	 *            
	 * @return double rounded up value
	 */
	private BigDecimal roundOff(BigDecimal value) {
		value = value.divide(ROUND_FACTOR);
		value = new BigDecimal(Math.ceil(value.doubleValue()));
		value = value.multiply(ROUND_FACTOR);
		return value;
	}
	
	public void calculateTax(Item item) {
		calculateTaxPercentage(item);
		calculateSalesTax(item);
	}

	/**
	 * exempt or apply tax percentage base on item type
	 * @param item for sale
	 *          
	 */
	private void calculateTaxPercentage(Item item) {

		if (item.getItemType().equalsIgnoreCase("book") 
				|| item.getItemType().equalsIgnoreCase("food")
				|| item.getItemType().equalsIgnoreCase("medical")) {
			taxPercentage = new BigDecimal("0.00");
		} else {
			taxPercentage = DOMESTIC_TAX_PERCENTAGE;
		}

		if (item.getItemType().equalsIgnoreCase("imported")) {
			taxPercentage = taxPercentage.add(IMPORTED_ITEMS_TAX_PERCENTAGE);
		}
	}

}