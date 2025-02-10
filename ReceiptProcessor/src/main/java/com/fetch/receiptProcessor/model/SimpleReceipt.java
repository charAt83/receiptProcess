package com.fetch.receiptProcessor.model;

import java.util.List;

public class SimpleReceipt {

	private String retailer;
	private String purchaseDate;
	private String purchaseTime;
	private String total;
	private List<Item> items;
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchasedDate) {
		this.purchaseDate = purchasedDate;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchasedTime) {
		this.purchaseTime = purchasedTime;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	

}
