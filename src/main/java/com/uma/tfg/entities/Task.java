package com.uma.tfg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    @Lob
    @Column
    private String description;
    private Integer priority;
    private Integer state;
    private Integer numHours;
    private Integer flagActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    @JsonIgnoreProperties(value= {"task"}, allowSetters=true)
    private Set<TaskComment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    @JsonIgnoreProperties(value= {"task"}, allowSetters=true)
    private Set<TaskImage> images;

    @ManyToOne
    @JsonIgnoreProperties(value= {"createdTasks" , "assignedTasks", "receivedMails", "writtenMails", "bills", "tasksComments", "productsComments"}, allowSetters=true)
    private User creator;
    
    @ManyToMany
    @JoinTable(
            name = "task_assigned",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties(value= {"createdTasks" , "assignedTasks", "receivedMails", "writtenMails", "bills", "tasksComments", "productsComments"}, allowSetters=true)
    private Set<User> assignedUsers;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    @JsonIgnoreProperties(value= {"assignedUsers" , "product", "task", "project"}, allowSetters=true)
    private Set<Activity> activities;
    
    @ManyToOne
    @JsonIgnoreProperties(value= {"tasks", "creator", "usersRelated"}, allowSetters=true)
    private Project project;

    public Task() {}

    public Task(String name, LocalDate creationDate, LocalDate endDate, String description, Integer priority,Integer state, Integer numHours, Long idCreator, String nameCreator) {
        this.name = name;
    	this.creationDate = creationDate;
        this.endDate = endDate;
        this.description = description;
        this.priority = priority;
        this.state = state;
        this.numHours = numHours;

        if(idCreator != null) {
            User user = new User();
            user.setId(idCreator);
            user.setName(nameCreator);
            this.creator = user;
        }
    }

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

	public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getNumHours() {
        return numHours;
    }

    public void setNumHours(Integer numHours) {
        this.numHours = numHours;
    }

    public Set<TaskComment> getComments() {
        return comments;
    }

    public void setComments(Set<TaskComment> comments) {
        this.comments = comments;
    }

    public Set<TaskImage> getImages() {
        return images;
    }

    public void setImages(Set<TaskImage> images) {
        this.images = images;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Set<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Integer getFlagActive() {
		return flagActive;
	}

	public void setFlagActive(Integer flagActive) {
		this.flagActive = flagActive;
	}  
	
}
