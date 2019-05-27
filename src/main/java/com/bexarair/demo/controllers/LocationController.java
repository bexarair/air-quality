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
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class LocationController {
    private static final String[] zipcodes = {"78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"};





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
        List<String>zipcodeList = new ArrayList<>();
        for(int i = 0; i < zipcodes.length; i++) {
            zipcodeList.add(zipcodes[i]);
        }
//        if (!zipcodeList.contains(locationToCreate.getZipcode())) {
//            viewModel.addAttribute("error", true);
//        }




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
                long locId = userLocations.get(j).getId();

                if(currentZipCode.equals(locationZipCode)) {

                    AqiZipCode aqiZipCode = new AqiZipCode();

                    aqiZipCode.setAqi(currentAqi);
                    aqiZipCode.setZipCode(locationZipCode);
                    aqiZipCode.setCategoryName(currentCatName);
                    aqiZipCode.setTitle(locationTitle);
                    aqiZipCode.setId(locId);

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

