package com.bexarair.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

//    @Column(nullable = false)
//    private boolean textAlert;
//
//    @Column(nullable = false)
//    private boolean dailyAlert;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserLocation> location;

//    @OneToOne
//    private User user;

    //Creating a User
    public User() {

    }



    //Reading and Updating the User
//    public User(String firstName, String lastName, String email, String username, String password, String phone, boolean textAlert, boolean dailyAlert) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.phone = phone;
//        this.textAlert = textAlert;
//        this.dailyAlert = dailyAlert;
//    }


    public User(String firstName, String lastName, String email, String username, String password, String phone, List<UserLocation> location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
//        this.textAlert = textAlert;
//        this.dailyAlert = dailyAlert;
        this.location = location;
    }

    public User(User copy) {
        id = copy.id;
        firstName = copy.firstName;
        lastName = copy.lastName;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        phone = copy.phone;
//        textAlert = copy.textAlert;
//        dailyAlert = copy.dailyAlert;
        location = copy.location;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public boolean isTextAlert() {
//        return textAlert;
//    }
//
//    public void setTextAlert(boolean textAlert) {
//        this.textAlert = textAlert;
//    }
//
//    public boolean isDailyAlert() {
//        return dailyAlert;
//    }
//
//    public void setDailyAlert(boolean dailyAlert) {
//        this.dailyAlert = dailyAlert;
//    }

    public List<UserLocation> getLocation() {
        return location;
    }

    public void setLocation(List<UserLocation> location) {
        this.location = location;
    }
}//end of user class
