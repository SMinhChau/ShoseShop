package com.shoeshop.entity;

import java.util.List;

public class ProductWrapper {
	List<Product> list;

	public void addProduct(Product product) {
		list.add(product);
	}
}
