package com.uma.tfg.entities;

import java.util.Set;


public class ProductRequest {

	private String profileImage;
    
    private Set<String> mainImages;

    private Product product;

    public ProductRequest() {}

    public ProductRequest(String profileImage, Set<String> mainImages , Product product) {
        this.mainImages = mainImages;
        
        this.product = product;
        
        this.profileImage = profileImage;
    }
    
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Set<String> getMainImages() {
		return mainImages;
	}

	public void setMainImages(Set<String> mainImages) {
		this.mainImages = mainImages;
	}
}