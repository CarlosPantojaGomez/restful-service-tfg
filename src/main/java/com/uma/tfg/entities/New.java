package com.uma.tfg.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class New {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
	private String sortDescription;
	private String title;
	private String description;
	
	@OneToOne
    @JsonIgnoreProperties(value= {"id" , "product"}, allowSetters=true)
    private ProductImage cardImage;
	
	public New() {}

    public New(String sortDescription, String title, String description, Long idNewCardImage) {
        this.sortDescription = sortDescription;
        this.title = title;
        this.description = description;

        if (idNewCardImage != null) {
            ProductImage productImage = new ProductImage();
            productImage.setId(idNewCardImage);
            this.cardImage = productImage;
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public ProductImage getcardImage() {
        return cardImage;
    }

    public void setcardImage(ProductImage cardImage) {
        this.cardImage = cardImage;
    }

}
