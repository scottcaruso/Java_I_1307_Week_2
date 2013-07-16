package com.scottcaruso.practiceecom;

public class Phone implements Product {
	
	String name;
	double price;
	
	public Phone(String name, double price){
		setName(name);
		setPrice(price);
	}

	@Override
	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	@Override
	public boolean setPrice(double price) {
		this.price = price;
		return true;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

}
