package com.uma.tfg.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Activity {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String action;
    private LocalDate activityDate;
	
	@ManyToMany
    @JsonIgnoreProperties(value= {"productsBought" , "bills", "tasksComments", "productsComments", "receivedMails", "writtenMails", "createdTasks", "assignedTasks", "gender", "country", "activitiesRelated", "activitiesCreated"}, allowSetters=true)
    @JoinTable(
            name = "user_assigned",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> assignedUsers;

	@ManyToOne
	@JsonIgnoreProperties(value= {"productsBought" , "bills", "tasksComments", "productsComments", "receivedMails", "writtenMails", "createdTasks", "assignedTasks", "gender", "country", "activitiesRelated", "activitiesCreated"}, allowSetters=true)
    private User creator;
	
	@ManyToOne
    @JsonIgnoreProperties(value= {"file" , "bills", "comments", "manuals", "profileImage", "images", "projects", "buyers"}, allowSetters=true)
    @JoinColumn(name="product_related")
    private Product product;
	
	@ManyToOne
    @JsonIgnoreProperties(value= {"comments", "images", "creator", "assignedUsers", "activities"}, allowSetters=true)
    @JoinColumn(name="task_related")
    private Task task;
	
	@ManyToOne
    @JsonIgnoreProperties(value= {"tasks", "product", "creator", "usersRelated", "activities"}, allowSetters=true)
    @JoinColumn(name="project_related")
    private Project project;
	
	@ManyToOne
    @JsonIgnoreProperties(value= {"writer" , "receiver", "activities"}, allowSetters=true)
    @JoinColumn(name="mail_related")
    private Mail mail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public LocalDate getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(LocalDate activityDate) {
		this.activityDate = activityDate;
	}
	public Set<User> getAssignedUsers() {
		return assignedUsers;
	}
	public void setAssignedUsers(Set<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
}
