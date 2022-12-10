package com.uma.tfg.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Manual {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"id" , "product", "manual"}, allowSetters=true)
    private File file;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
