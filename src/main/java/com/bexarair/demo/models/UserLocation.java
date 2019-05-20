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

    @Column
    private String city;

    @Column
    private String country;


    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private User user;


    public UserLocation(){

    }

    public UserLocation(String zipcode, String title, String city, String country, User user) {
        this.zipcode = zipcode;
        this.title = title;
        this.city = city;
        this.country = country;
        this.user = user;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
