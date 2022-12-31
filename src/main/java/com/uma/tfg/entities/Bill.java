package com.uma.tfg.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String billNumber;
    private LocalDate saleDate;
    private Double iva;
    private Double netValue;
    private Double grossValue;
    
    private String cif;
    private String companyName;
    private String companyAddress;
    

    private String address_line_1;
    private String address_line_2;
    private String admin_area_1;
    private String admin_area_2;
    private String country_code;
    private String postal_code;
    private String email_address;
    private String name;
    private String surname;
    private String national_number;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bill_products",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnoreProperties(value= {"file" , "bills", "relatedNews", "comments", "rates", "manuals", "profileImage", "images", "projects", "buyers"}, allowSetters=true)
    private Set<Product> products;

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
	
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public String getAddress_line_1() {
		return address_line_1;
	}

	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public String getAdmin_area_1() {
		return admin_area_1;
	}

	public void setAdmin_area_1(String admin_area_1) {
		this.admin_area_1 = admin_area_1;
	}

	public String getAdmin_area_2() {
		return admin_area_2;
	}

	public void setAdmin_area_2(String admin_area_2) {
		this.admin_area_2 = admin_area_2;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNational_number() {
		return national_number;
	}

	public void setNational_number(String national_number) {
		this.national_number = national_number;
	}
	
	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
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
