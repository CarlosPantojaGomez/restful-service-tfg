package com.uma.tfg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate creationDate;
    @Lob
    @Column
    private String description;
    @Lob
    @Column
    private String sortDescription;
    @Lob
    private String features;
    private Double price;
    private Double rating;
    private Boolean forSale;
    private Integer flagActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"id", "product", "manual"}, allowSetters=true)
    private File file;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnoreProperties(value= {"user", "product"}, allowSetters=true)
    private Set<Bill> bills;
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "productsRelated")
    @JsonIgnoreProperties(value= {"productsRelated", "creator"}, allowSetters=true)
    private Set<New> relatedNews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductComment> comments;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductRate> rates;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnoreProperties(value= {"product", "file"}, allowSetters=true)
    private Set<Manual> manuals;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"id" , "product"}, allowSetters=true)
    private ProductImage profileImage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnoreProperties(value= {"product"}, allowSetters=true)
    private Set<ProductImage> images;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnoreProperties(value= {"assignedUsers" , "product", "task", "project"}, allowSetters=true)
    private Set<Activity> activities;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<Project> projects;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_buyer",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties(value= {"tasksComments", "file", "bills", "manuals", "profileImage", "images", "projects", "projectsAssigned", "activitiesRelated", "productsBought", "createdTasks", "createdNews", "assignedTasks", "activitiesCreated", "productsComments", "projectsCreated", "rates", "receivedMails", "writtenMails"}, allowSetters=true)
    private Set<User> buyers;
    
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    @JsonIgnoreProperties(value= {"products"}, allowSetters=true)
    private Set<Basket> baskets;

    public Product() {}

    public Product(String name, String description, Double price, Double rating, Long idProductImage, Long idFile) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        if (idProductImage != null) {
            ProductImage productImage = new ProductImage();
            productImage.setId(idProductImage);
            this.profileImage = productImage;
        }
        if (idFile != null) {
            File file = new File();
            file.setId(idFile);
            this.file = file;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Set<Bill> getBills() {
        return bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public Set<ProductComment> getComments() {
        return comments;
    }

    public void setComments(Set<ProductComment> comments) {
        this.comments = comments;
    }

    public ProductImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProductImage profileImage) {
        this.profileImage = profileImage;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

	public Set<Manual> getManuals() {
		return manuals;
	}

	public void setManuals(Set<Manual> manuals) {
		this.manuals = manuals;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<User> getBuyers() {
		return buyers;
	}

	public void setBuyers(Set<User> buyers) {
		this.buyers = buyers;
	}

	public Boolean getForSale() {
		return forSale;
	}

	public void setForSale(Boolean forSale) {
		this.forSale = forSale;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getFlagActive() {
		return flagActive;
	}

	public void setFlagActive(Integer flagActive) {
		this.flagActive = flagActive;
	}

	public Set<ProductRate> getRates() {
		return rates;
	}

	public void setRates(Set<ProductRate> rates) {
		this.rates = rates;
	}

	public String getSortDescription() {
		return sortDescription;
	}

	public void setSortDescription(String sortDescription) {
		this.sortDescription = sortDescription;
	}

	public Set<New> getRelatedNews() {
		return relatedNews;
	}

	public void setRelatedNews(Set<New> relatedNews) {
		this.relatedNews = relatedNews;
	}

	public Set<Basket> getBaskets() {
		return baskets;
	}

	public void setBaskets(Set<Basket> baskets) {
		this.baskets = baskets;
	}
}
