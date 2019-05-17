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
    private int zipcode;

    @Column
    private String title;

    @Column
    private String city;

    @Column
    private String country;


    @ManyToMany(mappedBy = "locations")
    private List<User> users;

    //    @Column(nullable = false)
//    private String firstName;
//
//    @Column(nullable = false)
//    private String lastName;
//
//    @Column(nullable = false)
//    private String email;

}
