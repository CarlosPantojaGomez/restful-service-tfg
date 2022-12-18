package com.uma.tfg.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
    private Integer flagActive;
    @Lob
    @Column
	private String description;

    @Lob
    private String cardImage;
	
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    @JoinTable(
            name = "new_product_related",
            joinColumns = @JoinColumn(name = "new_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productsRelated;
	
	@ManyToOne(cascade = CascadeType.ALL)
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

	public Integer getFlagActive() {
		return flagActive;
	}

	public void setFlagActive(Integer flagActive) {
		this.flagActive = flagActive;
	}

	public Set<Product> getProductsRelated() {
		return productsRelated;
	}

	public void setProductsRelated(Set<Product> productsRelated) {
		this.productsRelated = productsRelated;
	}
}
