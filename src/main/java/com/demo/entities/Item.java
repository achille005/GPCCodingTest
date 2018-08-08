package com.demo.entities;

import java.math.BigDecimal;
/**
 * Model an item
 * 
 * @author Achille
 *
 */
public class Item {

	private BigDecimal cost;
	private BigDecimal salesTax;
	private String item;
	private String itemType;
	private String name;

	public Item(String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax) {
		this.salesTax = salesTax;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
