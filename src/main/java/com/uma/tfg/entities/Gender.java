package com.uma.tfg.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Gender {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String gender;

    @OneToMany(mappedBy = "gender")
    private Set<User> user;
    
    public Gender() {}

    public Gender(String gender) {
        this.gender = gender;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}
    
    
}
