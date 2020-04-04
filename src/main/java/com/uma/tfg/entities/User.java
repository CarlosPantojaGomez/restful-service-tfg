package com.uma.tfg.entities;

import javax.persistence.*;
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
    private Integer flagActive;

    @OneToMany(mappedBy = "user")
    private Set<Bill> bills;

    @OneToMany(mappedBy = "creator")
    private Set<TaskComment> tasksComments;

    @OneToMany(mappedBy = "creator")
    private Set<ProductComment> productsComments;

    @OneToMany(mappedBy = "receiver")
    private Set<Mail> receivedMails;

    @OneToMany(mappedBy = "writer")
    private Set<Mail> writtenMails;

    @OneToMany(mappedBy = "creator")
    private Set<Task> createdTasks;

    @ManyToMany
    @JoinTable(
            name = "task_assigned",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> assignedTasks;

    public User() {}

    public User(String name, String firstLastName, String secondLastName, String email, String nickname, String password, Integer userType, Integer flagActive) {
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
        this.flagActive = flagActive;
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
