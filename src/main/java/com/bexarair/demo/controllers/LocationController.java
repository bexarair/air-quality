package com.bexarair.demo.controllers;

import com.bexarair.demo.models.User;
//import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.repositories.LocationRepository;
import com.bexarair.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LocationController {

    private UserRepository userCRUD;
    private LocationRepository locationCRUD;
    private PasswordEncoder passwordEncoder;

    public LocationController(UserRepository userCRUD, LocationRepository locationCRUD, PasswordEncoder passwordEncoder) {
        this.userCRUD = userCRUD;
        this.locationCRUD = locationCRUD;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/save-location")
    public String getLocation(Model model) {
        model.addAttribute("location", new UserLocation());
        return "users/user-profile";
    }

    @PostMapping("/save-location")
    public String saveLocation(@ModelAttribute UserLocation locationToCreate){

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userCRUD.findOne(sessionUser.getId());

        locationToCreate.setUser(userDB);

        locationCRUD.save(locationToCreate);
        return "redirect:/profile";
    }
}

