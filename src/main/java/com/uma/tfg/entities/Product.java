package com.uma.tfg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double rating;

    @OneToMany(mappedBy = "user")
    private Set<Bill> bills;

    @OneToMany(mappedBy = "product")
    private Set<ProductComment> comments;

    @OneToOne
    @JsonIgnoreProperties(value= {"id" , "product"}, allowSetters=true)
    private ProductImage profileImage;

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images;

    public Product() {}

    public Product(String name, String description, Double price, Double rating, Long idProductImage) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        if (idProductImage != null) {
            ProductImage productImage = new ProductImage();
            productImage.setId(idProductImage);
            this.profileImage = productImage;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public Set<ProductComment> getComments() {
        return comments;
    }

    public void setComments(Set<ProductComment> comments) {
        this.comments = comments;
    }

    public ProductImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProductImage profileImage) {
        this.profileImage = profileImage;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }
}
