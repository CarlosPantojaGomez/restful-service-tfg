package com.uma.tfg.entities;

import java.util.Set;


public class ProductRequest {

	private ProductImage profileImage;
    
    private Set<ProductImage> mainImages;

    private Product product;

    private File file;
    
    private Set<File> manuals;

	public ProductRequest() {}

    public ProductRequest(File file, ProductImage profileImage, Set<ProductImage> mainImages, Product product, Set<File> manuals) {
        this.mainImages = mainImages;
        
        this.product = product;
        
        this.profileImage = profileImage;
        
        this.file = file;
    }

	public ProductImage getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(ProductImage profileImage) {
		this.profileImage = profileImage;
	}

	public Set<ProductImage> getMainImages() {
		return mainImages;
	}

	public void setMainImages(Set<ProductImage> mainImages) {
		this.mainImages = mainImages;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Set<File> getManuals() {
		return manuals;
	}

	public void setManuals(Set<File> manuals) {
		this.manuals = manuals;
	}
}