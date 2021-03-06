package com.uma.tfg.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private LocalDate creationDate;
    private String subject;
    private String text;

    @ManyToOne
    @JsonIgnoreProperties(value= {"writtenMails"}, allowSetters=true)
    private User writer;

    @ManyToOne
    @JsonIgnoreProperties(value= {"receivedMails"}, allowSetters=true)
    private User receiver;

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
}
