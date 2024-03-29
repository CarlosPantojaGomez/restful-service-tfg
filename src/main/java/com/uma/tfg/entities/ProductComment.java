package com.uma.tfg.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@Entity
public class ProductComment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private LocalDate creationDate;
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects", "tasksComments"}, allowSetters=true)
    private User creator;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public ProductComment() {}

    public ProductComment(LocalDate creationDate, String description, Long idCreator, String nameCreator, Long idProduct) {
        this.creationDate = creationDate;
        this.text = description;

        if(idCreator != null) {
            User user = new User();
            user.setId(idCreator);
            user.setName(nameCreator);
            this.creator = user;
        }
        if(idProduct != null) {
            Product product = new Product();
            product.setId(idProduct);
            this.product = product;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
