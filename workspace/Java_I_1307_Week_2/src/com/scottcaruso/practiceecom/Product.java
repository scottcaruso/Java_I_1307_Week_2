package com.scottcaruso.practiceecom;

public interface Product {

	//set the name of a product
	public boolean setName(String name);
	
	//set the price of a product
	public boolean setPrice(double price);
	
	//get the name of a product
	public String getName();
	
	//get the price of a product
	public double getPrice();
}
