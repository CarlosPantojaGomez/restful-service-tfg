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
    private String features;
    private Double price;
    private Double rating;
    private Boolean forSale;

    @OneToOne
    @JsonIgnoreProperties(value= {"id" , "product"}, allowSetters=true)
    private File file;
    
    @OneToMany(mappedBy = "user")
    private Set<Bill> bills;
    
    @OneToMany(mappedBy = "creator")
    private Set<New> news;

    @OneToMany(mappedBy = "product")
    private Set<ProductComment> comments;
    
    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value= {"product"}, allowSetters=true)
    private Set<Manual> manuals;

    @OneToOne
    @JsonIgnoreProperties(value= {"id" , "product"}, allowSetters=true)
    private ProductImage profileImage;

    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value= {"product"}, allowSetters=true)
    private Set<ProductImage> images;
    
    @OneToMany(mappedBy = "product")
    @JsonIgnoreProperties(value= {"assignedUsers" , "product", "task", "project"}, allowSetters=true)
    private Set<Activity> activities;
    
    @OneToMany(mappedBy = "product")
    private Set<Project> projects;
    
    @ManyToMany
    @JoinTable(
            name = "user_buyer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<User> buyers;

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

	public Set<New> getNews() {
		return news;
	}

	public void setNews(Set<New> news) {
		this.news = news;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
}
