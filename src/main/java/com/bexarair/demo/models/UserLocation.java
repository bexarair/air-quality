package com.bexarair.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="locations")
public class UserLocation {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String zipcode;

    @Column
    private String title;

    @Column(nullable = false)
    private boolean textAlert;

    @Column(nullable = false)
    private boolean dailyAlert;



    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private User user;


    public UserLocation(){}

    public UserLocation(String zipcode, String title, User user, boolean textAlert, boolean dailyAlert) {
        this.zipcode = zipcode;
        this.title = title;
        this.user = user;
        this.textAlert = textAlert;
        this.dailyAlert = dailyAlert;
    }

    public UserLocation(String zipcode, String title, boolean textAlert, boolean dailyAlert) {
        this.zipcode = zipcode;
        this.title = title;
        this.textAlert = textAlert;
        this. dailyAlert = dailyAlert;

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTextAlert(boolean textAlert){
        this.textAlert = textAlert;
    }

    public boolean getTextAlert(){
        return textAlert;
    }

    public void setDailyAlert(boolean dailyAlert){
        this.dailyAlert = dailyAlert;
    }

    public boolean getDailyAlert(){
        return dailyAlert;
    }

}
