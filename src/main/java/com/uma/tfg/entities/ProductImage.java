package com.uma.tfg.entities;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

import java.sql.Clob;

@Entity
public class ProductImage implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Lob
    private String url;

    @ManyToOne
    private Product product;

    public ProductImage() {}

    public ProductImage(String url, Long idProductProfile , Long idProduct) {
        this.url = url;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
