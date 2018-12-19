package com.cxl.rewards.model;

public class FeaturedItem {

	private int id = 0;
	private String itemDescription;
	private String itemCode;
	private String itemPointValue;
	
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemPointValue() {
		return itemPointValue;
	}
	public void setItemPointValue(String itemPointValue) {
		this.itemPointValue = itemPointValue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
