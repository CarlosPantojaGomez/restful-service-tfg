package com.uma.tfg.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private Double amount;
    
    @ManyToMany
    @JoinTable(
            name = "product_baskets",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnoreProperties(value= {"file" , "bills", "relatedNews", "comments", "rates", "manuals", "images", "projects", "buyers", "baskets", "activities"}, allowSetters=true)
    private Set<Product> products;
    
    @OneToOne
    @JsonIgnoreProperties(value= {"tasksComments", "file", "bills", "manuals", "profileImage", "images", "projects", "projectsAssigned", "activitiesRelated", "productsBought", "createdTasks", "createdNews", "assignedTasks", "activitiesCreated", "productsComments", "projectsCreated", "rates", "receivedMails", "writtenMails"}, allowSetters=true)
    private User user;
    

    public Basket() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
}
