package com.cxl.rewards.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Account Information, populated from Gateway API
 * @author rmoon
 *
 */
public class AccountInfo 
{
	public AccountInfo() {}
	
    public String accountNumber ="";
    public String accountPIN ="";
    public String accountBalance ="";
    
    public List<RedemptionItem> redemptionItemList;
    
    public void addRedemption(String redemptionId, String itemDescription, String redemptionDate) 
    {
    	if(redemptionItemList == null) {
    		redemptionItemList = new ArrayList<RedemptionItem>();
    	}
    	
    	RedemptionItem item = new RedemptionItem();
    	item.setRedemptionId(redemptionId);
    	item.setItemDescription(itemDescription);
    	item.setRedemptionDate(redemptionDate);
    	redemptionItemList.add(item);
    }
    
	public List<RedemptionItem> getRedemptionItemList() {
		return redemptionItemList;
	}
	public void setRedemptionItemList(List<RedemptionItem> redemptionItemList) {
		this.redemptionItemList = redemptionItemList;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountPIN() {
		return accountPIN;
	}
	public void setAccountPIN(String accountPIN) {
		this.accountPIN = accountPIN;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
    
    
}
