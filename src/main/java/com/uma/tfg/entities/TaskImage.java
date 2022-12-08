package com.uma.tfg.entities;

import javax.persistence.*;

@Entity
public class TaskImage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Lob
    private String url;

    @ManyToOne
    private Task task;

    public TaskImage() {}

    public TaskImage(String url, Long idTask) {
        this.url = url;
        if (idTask != null) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
