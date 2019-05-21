package com.bexarair.demo.controllers;

import com.bexarair.demo.models.User;
import com.bexarair.demo.models.UserLocation;
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
/****************Dependancy Injection********************/
    private UserRepository userCRUD;
    private LocationRepository locationCRUD;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository user, LocationRepository location, PasswordEncoder passwordEncoder) {
        this.userCRUD = user;
        this.locationCRUD = location;
        this.passwordEncoder = passwordEncoder;
    }
/*********************************************************/

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("location1", new UserLocation());
        model.addAttribute("location2", new UserLocation());
        model.addAttribute("location3", new UserLocation());
        return "users/sign-up";
    }

//    @GetMapping("/profile")
//    public String showUserProfile(Model model){
//        model.addAttribute("user", new User());
//        model.addAttribute("location", new UserLocation());
//        model.addAttribute("location2", new UserLocation());
//        model.addAttribute("location3", new UserLocation()); // i just added this
//        return "users/user-profile";
//    }

    // this isn't working as of 05/19
    @GetMapping("/profile/edit")
    public String showEditProfile(Model model){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userCRUD.findOne(sessionUser.getId());
//        UserLocation location = locationCRUD.findOne(id);
        model.addAttribute("user", userDB);
        return "users/edit-profile";
    }

    // this isn't working as of 05/19
    @PostMapping("/profile/edit")
    public String saveEditedUser(@ModelAttribute User userToEdit){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String hash = passwordEncoder.encode(userToEdit.getPassword());
        userToEdit.setId(sessionUser.getId());
        userToEdit.setPassword(hash);
        userToEdit.setFirstName(sessionUser.getFirstName());
        userToEdit.setLastName(sessionUser.getLastName());
        userCRUD.save(userToEdit);
        return "redirect:/profile";
    }


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
