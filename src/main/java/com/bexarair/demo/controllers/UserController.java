package com.bexarair.demo.controllers;

import com.bexarair.demo.models.User;
import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.repositories.LocationRepository;
import com.bexarair.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private LocationRepository locationCRUD;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model){
        model.addAttribute("user", new User());
        return "users/user-profile";
    }

    // this isn't working as of 05/19
    @GetMapping("/profile/{id}/edit")
    public String showEditProfile(@PathVariable long id, Model model){
        UserLocation location = locationCRUD.findOne(id);
        model.addAttribute("user", new User());
        return "users/edit-profile";
    }

    // this isn't working as of 05/19
    @PostMapping("/profile/{id}/edit")
    public String saveEditedUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

//    @GetMapping("/profile")
//    public String showUserProfile(Model model){
//        model.addAttribute("user", new User());
//        return "users/profile";
//    }
}
