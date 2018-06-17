package com.javacart.beans;

import java.awt.Image;

public class ItemBean {
	
	private int itemId;
	private String itemName;
	private int price;
	private Image image;
	private boolean checked;
	private char availability; 
	private int condition;
	private int rating;
	private int quantityinCart;
	private int totalPrice;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public char getAvailability() {
		return availability;
	}
	public void setAvailability(char availability) {
		this.availability = availability;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getQuantityinCart() {
		return quantityinCart;
	}
	public void setQuantityinCart(int quantityinCart) {
		this.quantityinCart = quantityinCart;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
