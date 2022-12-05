package com.uma.tfg.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Project {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @Lob
    @Column
    private String description;
    private Double priority;
    
    @ManyToMany(mappedBy = "project")
    @JsonIgnoreProperties(value= {"comments", "images", "creator", "assignedUsers", "project", "activities"}, allowSetters=true)
    private Set<Task> tasks;
    
    @ManyToOne
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties(value= {"file", "bills", "manuals", "profileImage", "images", "projects"}, allowSetters=true)
    private User creator;
    

    @ManyToMany
    @JoinTable(
            name = "project_assigned",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties(value= {"tasksComments"}, allowSetters=true)
    private Set<User> usersRelated;
    
    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value= {"assignedUsers" , "product", "task", "project"}, allowSetters=true)
    private Set<Activity> activities;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPriority() {
		return priority;
	}

	public void setPriority(Double priority) {
		this.priority = priority;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Set<User> getUsersRelated() {
		return usersRelated;
	}

	public void setUsersRelated(Set<User> usersRelated) {
		this.usersRelated = usersRelated;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}	
}
