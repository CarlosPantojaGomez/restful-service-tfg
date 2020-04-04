package com.uma.tfg.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class TaskComment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private LocalDate creationDate;
    private String text;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Task task;

    public TaskComment() {}

    public TaskComment(LocalDate creationDate, String description, Long idCreator, String nameCreator, Long idTask) {
        this.creationDate = creationDate;
        this.text = description;

        if(idCreator != null) {
            User user = new User();
            user.setId(idCreator);
            user.setName(nameCreator);
            this.creator = user;
        }
        if(idTask != null) {
            Task task = new Task();
            task.setId(idTask);
            this.task = task;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
