package com.uma.tfg.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class New {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Lob
    @Column
	private String sortDescription;
	private String title;
    private LocalDate creationDate;
    @Lob
    @Column
	private String description;

    @Lob
    private String cardImage;
	
	@ManyToOne
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    private Product product;
	
	@ManyToOne
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    private User creator;
	
	public New() {}

    public New(String sortDescription, String title, String description, Long idNewCardImage) {
        this.sortDescription = sortDescription;
        this.title = title;
        this.description = description;

        if (idNewCardImage != null) {
            ProductImage productImage = new ProductImage();
            productImage.setId(idNewCardImage);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getCardImage() {
		return cardImage;
	}

	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	
}
