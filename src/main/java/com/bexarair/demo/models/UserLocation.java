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



    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private User user;


    public UserLocation(){}

    public UserLocation(String zipcode, String title, User user) {
        this.zipcode = zipcode;
        this.title = title;
        this.user = user;
    }

    public UserLocation(String zipcode, String title) {
        this.zipcode = zipcode;
        this.title = title;

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
}
