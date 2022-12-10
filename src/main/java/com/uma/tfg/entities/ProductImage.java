package com.uma.tfg.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Lob
    private String url;
    private Integer imageType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"description" , "price", "rating" , "bills", "comments", "profileImage", "images"}, allowSetters=true)
    private Product product;

    public ProductImage() {}

    public ProductImage(String url, Long idProductProfile , Integer imageType, Long idProduct) {
        this.url = url;
        this.imageType = imageType;
        
        if (idProduct != null) {
            Product product = new Product();
            product.setId(idProduct);
            this.product = product;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getImageType() {
		return imageType;
	}

	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}

	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
