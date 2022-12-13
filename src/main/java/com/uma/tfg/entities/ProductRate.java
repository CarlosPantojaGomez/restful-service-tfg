package com.uma.tfg.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ProductRate {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
	
    private Double rate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"description" , "price", "rating" , "bills", "comments", "profileImage", "images", "rates", "projects"}, allowSetters=true)
    private Product product;
    

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value= {"productsBought" , "bills", "tasksComments", "productsComments", "receivedMails", "writtenMails", "createdTasks", "assignedTasks", "gender", "country", "activitiesRelated", "activitiesCreated", "rates"}, allowSetters=true)
    private User rater;

    public ProductRate() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getRater() {
		return rater;
	}

	public void setRater(User rater) {
		this.rater = rater;
	}
}
