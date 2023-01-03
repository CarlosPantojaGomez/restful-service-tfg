package com.uma.tfg.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String nickname;
    private String password;
    private Integer userType;
    
    private Integer tlf;
    private LocalDate birthday;
    private String city;
    private String address;
    private Integer zipcode;
    
    @Lob
    private String profilePicture;
    private Integer flagActive;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnoreProperties(value= {"user" , "product"}, allowSetters=true)
    private Set<Bill> bills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    @JsonIgnoreProperties(value= {"creator" , "task"}, allowSetters=true)
    private Set<TaskComment> tasksComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    @JsonIgnoreProperties(value= {"creator" , "product"}, allowSetters=true)
    private Set<ProductComment> productsComments;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnoreProperties(value= {"writer" , "receiver", "activities"}, allowSetters=true)
    private Set<Mail> receivedMails;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rater")
    @JsonIgnoreProperties(value= {"product" , "rater"}, allowSetters=true)
    private Set<ProductRate> rates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "writer")
    @JsonIgnoreProperties(value= {"writer" , "receiver", "activities"}, allowSetters=true)
    private Set<Mail> writtenMails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    @JsonIgnoreProperties(value= {"comments" , "images", "creator", "assignedUsers", "activities", "project"}, allowSetters=true)
    private Set<Task> createdTasks;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    @JsonIgnoreProperties(value= {"productsRelated" , "creator"}, allowSetters=true)
    private Set<New> createdNews;

    @ManyToMany( mappedBy = "assignedUsers")
    @JsonIgnoreProperties(value= {"comments" , "images", "creator", "assignedUsers", "activities", "project"}, allowSetters=true)
    private Set<Task> assignedTasks;

    @ManyToMany( mappedBy = "usersRelated")
    @JsonIgnoreProperties(value= {"tasks", "product", "creator", "usersRelated", "activities"}, allowSetters=true)
    private Set<Project> projectsAssigned;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    @JsonIgnoreProperties(value= {"tasks", "product", "creator", "usersRelated", "activities"}, allowSetters=true)
    private Set<Project> projectsCreated;
    
    
    @ManyToMany
    @JoinTable(
            name = "user_buyer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id "))
    @JsonIgnoreProperties(value= {"file" , "bills", "relatedNews", "comments", "rates", "manuals", "images", "projects", "buyers"}, allowSetters=true)
    private Set<Product> productsBought;
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "assignedUsers")
    @JsonIgnoreProperties(value= {"assignedUsers" , "creator", "product", "task", "project", "mail"}, allowSetters=true)
    private Set<Activity> activitiesRelated;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    @JsonIgnoreProperties(value= {"assignedUsers" , "creator", "product", "task", "project", "mail"}, allowSetters=true)
    private Set<Activity> activitiesCreated;
    
    @ManyToOne
    @JsonIgnoreProperties(value= {"user"}, allowSetters=true)
    @JoinColumn(name="user_place")
    private Country country;
    
    @ManyToOne
    @JsonIgnoreProperties(value= {"user"}, allowSetters=true)
    @JoinColumn(name="user_gender")
    private Gender gender;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"user"}, allowSetters=true)
    private Basket basket;
    
    public User() {}

    public User(String name, String firstLastName, String secondLastName, String email, String nickname, String password, String profilePicture, Integer userType, Integer flagActive, Long idCountry, String nameCountry, Long idGender, String gender) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
        this.profilePicture = profilePicture;
        this.flagActive = flagActive;
        
        if(idCountry != null) {
            Country country = new Country();
            country.setId(idCountry);
            country.setName(nameCountry);
            this.country = country;
        }
        
        if(idGender != null) {
            Gender gen = new Gender();
            gen.setId(idGender);
            gen.setGender(gender);
            this.gender = gen;
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

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Integer getFlagActive() {
        return flagActive;
    }

    public void setFlagActive(Integer flagActive) {
        this.flagActive = flagActive;
    }

    public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public Set<TaskComment> getTasksComments() {
        return tasksComments;
    }

    public void setTasksComments(Set<TaskComment> tasksComments) {
        this.tasksComments = tasksComments;
    }

    public Set<ProductComment> getProductsComments() {
        return productsComments;
    }

    public void setProductsComments(Set<ProductComment> productsComments) {
        this.productsComments = productsComments;
    }

    public Set<Mail> getReceivedMails() {
        return receivedMails;
    }

    public void setReceivedMails(Set<Mail> receivedMails) {
        this.receivedMails = receivedMails;
    }

    public Set<Mail> getWrittenMails() {
        return writtenMails;
    }

    public void setWrittenMails(Set<Mail> writtenMails) {
        this.writtenMails = writtenMails;
    }

    public Set<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(Set<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public Set<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(Set<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getTlf() {
		return tlf;
	}

	public void setTlf(Integer tlf) {
		this.tlf = tlf;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Set<New> getCreatedNews() {
		return createdNews;
	}

	public void setCreatedNews(Set<New> createdNews) {
		this.createdNews = createdNews;
	}

	public Set<Project> getProjectsAssigned() {
		return projectsAssigned;
	}

	public void setProjectsAssigned(Set<Project> projectsAssigned) {
		this.projectsAssigned = projectsAssigned;
	}

	public Set<Project> getProjectsCreated() {
		return projectsCreated;
	}

	public void setProjectsCreated(Set<Project> projectsCreated) {
		this.projectsCreated = projectsCreated;
	}

	public Set<Product> getProductsBought() {
		return productsBought;
	}

	public void setProductsBought(Set<Product> productsBought) {
		this.productsBought = productsBought;
	}

	public Set<Activity> getActivitiesRelated() {
		return activitiesRelated;
	}

	public void setActivitiesRelated(Set<Activity> activitiesRelated) {
		this.activitiesRelated = activitiesRelated;
	}

	public Set<Activity> getActivitiesCreated() {
		return activitiesCreated;
	}

	public void setActivitiesCreated(Set<Activity> activitiesCreated) {
		this.activitiesCreated = activitiesCreated;
	}
	
	public Set<ProductRate> getRates() {
		return rates;
	}

	public void setRates(Set<ProductRate> rates) {
		this.rates = rates;
	}
		
	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
