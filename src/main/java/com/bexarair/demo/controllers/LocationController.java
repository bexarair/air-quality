package com.bexarair.demo.controllers;

import com.bexarair.demo.models.*;
//import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.repositories.AirQualityRepository;
import com.bexarair.demo.repositories.ForecastRepository;
import com.bexarair.demo.repositories.LocationRepository;
import com.bexarair.demo.repositories.UserRepository;
import com.bexarair.demo.services.SmsSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LocationController {

    private UserRepository userCRUD;
    private AirQualityRepository airCRUD;
    private LocationRepository locationCRUD;
    private PasswordEncoder passwordEncoder;
    private SmsSender textService;


    public LocationController(UserRepository userCRUD, AirQualityRepository airCRUD, LocationRepository locationCRUD, PasswordEncoder passwordEncoder, SmsSender textService) {
        this.userCRUD = userCRUD;
        this.airCRUD = airCRUD;
        this.locationCRUD = locationCRUD;
        this.passwordEncoder = passwordEncoder;
        this.textService = textService;
    }


    @GetMapping("/locations/create")
    public String getLocation(Model model, Model viewModel) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long user = sessionUser.getId();
        viewModel.addAttribute("locations", locationCRUD.findAllByUserId(user));
        model.addAttribute("location", new UserLocation());
        return "locations/create";
    }

    //using this as a test case for text messages
    @PostMapping("/locations/create")
    public String saveLocation(@ModelAttribute UserLocation locationToCreate){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userCRUD.findOne(sessionUser.getId());
        locationToCreate.setUser(userDB);
        locationCRUD.save(locationToCreate);
//        textService.prepareAndSend();
        return "redirect:/profile";
    }


    @GetMapping("/locations/edit/{id}") //change this to post later
    protected String editLocation(@PathVariable long id, Model model, Model viewModel){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long user = sessionUser.getId();
        viewModel.addAttribute("locations", locationCRUD.findAllByUserId(user));
        UserLocation location = locationCRUD.findOne(id);
        model.addAttribute("location", location);
        return "locations/edit";
    }

    @PostMapping("/locations/edit/{id}")
    public String editLocation(@ModelAttribute UserLocation locationToEdit){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userCRUD.findOne(sessionUser.getId());
        locationToEdit.setUser(userDB);
        locationCRUD.save(locationToEdit);
//        return "redirect:/posts/" + postToEdit.getId();
        return "redirect:/profile";
    }



    @GetMapping("/locations/delete/{id}")
    public String deleteLocation(@PathVariable long id){
        locationCRUD.delete(id);
        return "redirect:/profile";
    }


    @GetMapping("/profile")
    public String showUserLocations(Model viewModel, Model viewModel2, Model viewModel3){
        Date dt = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dt);

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long user = sessionUser.getId();
        List<UserLocation> userLocations = locationCRUD.findAllByUserId(user);
        List<AirQualityRecord> current = airCRUD.findAllByDateObserved(date);



        viewModel.addAttribute("locations", userLocations);

        if(locationCRUD.findAllByUserId(user) != null){
            viewModel2.addAttribute("edit", true);
        }
        if(locationCRUD.findAllByUserId(user).size() < 3){
            viewModel3.addAttribute("create", true);
        }

        ArrayList<AqiZipCode> aqiZipCodes = new ArrayList<>();


        for(int i = 0; i < current.size(); i++){
            String currentZipCode = current.get(i).getZipCode();
            String currentCatName = current.get(i).getCategoryName();
            int currentAqi = current.get(i).getAQI();
            for(int j = 0; j < userLocations.size(); j ++){
                String locationZipCode = userLocations.get(j).getZipcode();
                String locationTitle = userLocations.get(j).getTitle();
                long userId = userLocations.get(j).getUser().getId();

                if(currentZipCode.equals(locationZipCode)) {

                    AqiZipCode aqiZipCode = new AqiZipCode();

                    aqiZipCode.setAqi(currentAqi);
                    aqiZipCode.setZipCode(locationZipCode);
                    aqiZipCode.setCategoryName(currentCatName);
                    aqiZipCode.setTitle(locationTitle);
                    aqiZipCode.setId(user);

                    aqiZipCodes.add(aqiZipCode);
                }
                System.out.println(locationCRUD.findAllByUserId(user));
                System.out.println(locationCRUD.findAllByUserId(user).size());
            }
        }

        viewModel.addAttribute("aqiZipCodes", aqiZipCodes);
        return "users/user-profile";

    }




}

