package com.uma.tfg.entities;


public class UserRequest {

	private Product product;
	
	private User user;

    public UserRequest() {}

    public UserRequest(Product product, User user) {
        
        this.product = product;
        this.user = user;
    }
    
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
