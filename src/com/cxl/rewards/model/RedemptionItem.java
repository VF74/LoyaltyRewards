package com.cxl.rewards.model;

/**
 * Represents a single redemption
 * 
 * @author rmoon
 *
 */
public class RedemptionItem 
{
	public RedemptionItem() {}
	
	public String redemptionId;
	public String itemDescription;
	public String redemptionDate;
	
	public String getRedemptionId() {
		return redemptionId;
	}
	public void setRedemptionId(String redemptionId) {
		this.redemptionId = redemptionId;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getRedemptionDate() {
		return redemptionDate;
	}
	public void setRedemptionDate(String redemptionDate) {
		this.redemptionDate = redemptionDate;
	}
	
}
