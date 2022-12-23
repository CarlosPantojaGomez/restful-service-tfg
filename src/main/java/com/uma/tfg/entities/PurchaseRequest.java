package com.uma.tfg.entities;

public class PurchaseRequest {

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
	private Basket basket;

	public PurchaseRequest() {}

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

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
}
