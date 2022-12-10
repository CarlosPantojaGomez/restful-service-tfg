package com.uma.tfg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDate creationDate;
    private String subject;
    private String text;
    private String writerName;
    private String receiverName;
    private boolean saw;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"writtenMails"}, allowSetters=true)
    private User writer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value= {"receivedMails"}, allowSetters=true)
    private User receiver;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mail")
    @JsonIgnoreProperties(value= {"assignedUsers" , "product", "task", "project"}, allowSetters=true)
    private Set<Activity> activities;

    public Mail() {}

    public Mail(LocalDate creationDate, String subject, String text, Long idWriter, String nameWriter, Long idReceiver, String nameReceiver) {
        this.creationDate = creationDate;
        this.subject = subject;
        this.text = text;

        if(idWriter != null) {
            User user = new User();
            user.setId(idWriter);
            user.setName(nameWriter);
            this.writer = user;
        }

        if(idReceiver != null) {
            User user = new User();
            user.setId(idReceiver);
            user.setName(nameReceiver);
            this.receiver = user;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public boolean isSaw() {
		return saw;
	}

	public void setSaw(boolean saw) {
		this.saw = saw;
	}
}
