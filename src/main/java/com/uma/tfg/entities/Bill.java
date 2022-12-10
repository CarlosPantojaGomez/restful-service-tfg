package com.uma.tfg.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private LocalDate saleDate;
    private Double iva;
    private Double netValue;
    private Double grossValue;
    private String companyName;
    private String cif;
    private String companyAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    @JoinColumn(name="product_id")
    private Product product;

    public Bill() {}

    public Bill(LocalDate saleDate, Double iva, String companyName, String cif, String companyAddress, Long idUser, String nameUser, Long idProduct, String nameProduct) {
        this.saleDate = saleDate;
        this.iva = iva;
        this.companyName = companyName;
        this.cif = cif;
        this.companyAddress = companyAddress;

        if(idUser != null) {
            User user = new User();
            user.setId(idUser);
            user.setName(nameUser);
            this.user = user;
        }

        if(idProduct != null) {
            Product product = new Product();
            user.setId(idProduct);
            user.setName(nameProduct);
            this.product = product;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Double getNetValue() {
		return netValue;
	}

	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}

	public Double getGrossValue() {
		return grossValue;
	}

	public void setGrossValue(Double grossValue) {
		this.grossValue = grossValue;
	}

	@Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", iva=" + iva +
                ", companyName='" + companyName + '\'' +
                ", cif='" + cif + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
    }


}
