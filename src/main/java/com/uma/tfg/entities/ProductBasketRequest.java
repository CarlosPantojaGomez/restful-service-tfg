package com.uma.tfg.entities;


public class ProductBasketRequest {


    private Product product;

    private User user;

	public ProductBasketRequest() {}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
