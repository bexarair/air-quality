package com.bexarair.demo.controllers;

import com.bexarair.demo.models.User;
import com.bexarair.demo.repositories.LocationRepository;
import com.bexarair.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    private UserRepository userCRUD;
    private LocationRepository locationCRUD;
    private PasswordEncoder passwordEncoder;
//    private LocationRepository locationCRUD;

    public UserController(UserRepository user, LocationRepository location, PasswordEncoder passwordEncoder) {
        this.userCRUD = user;
        this.locationCRUD = location;
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
//    @GetMapping("/profile/{id}/edit")
//    public String showEditProfile(@PathVariable long id, Model model){
//        UserLocation location = locationCRUD.findOne(id);
//        model.addAttribute("user", new User());
//        return "users/edit-profile";
//    }
//
//    // this isn't working as of 05/19
//    @PostMapping("/profile/{id}/edit")
//    public String saveEditedUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        users.save(user);
//        return "redirect:/login";
//    }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userCRUD.save(user);
        return "redirect:/login";
    }

//    @GetMapping("/profile")
//    public String showUserProfile(Model model){
//        model.addAttribute("user", new User());
//        return "users/profile";
//    }

    //should this be in a location controller?
    @GetMapping("/graph")
    public String showGraph(
//            Model viewModel
    ){
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long user = sessionUser.getId();
//        viewModel.addAttribute("locations",locationCRUD.findAllByUsersId(user));
        return "users/graph-test";
    }

}
