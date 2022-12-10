package com.uma.tfg.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class File  {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String type;
	
	@Lob
	private String data;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"description" , "price", "rating" , "bills", "comments", "profileImage", "images"}, allowSetters=true)
    private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"product"}, allowSetters=true)
    private Manual manual;
	
	public File() {
	}

	  public File(String name, String type, String data) {
	    this.name = name;
	    this.type = type;
	    this.data = data;
	  }
	
	  public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
	    return id;
	  }
	
	  public String getName() {
	    return name;
	  }
	
	  public void setName(String name) {
	    this.name = name;
	  }
	
	  public String getType() {
	    return type;
	  }
	
	  public void setType(String type) {
	    this.type = type;
	  }
	
	  public String getData() {
	    return data;
	  }
	
  public void setData(String data) {
    this.data = data;
  }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Manual getManual() {
		return manual;
	}

	public void setManual(Manual manual) {
		this.manual = manual;
	}
	  
	  

}
