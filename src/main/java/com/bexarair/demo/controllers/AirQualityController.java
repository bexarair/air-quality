package com.bexarair.demo.controllers;

import com.bexarair.demo.models.AirQualityRecord;
import com.bexarair.demo.models.ForecastRecord;
import com.bexarair.demo.models.User;
import com.bexarair.demo.models.UserLocation;


import com.bexarair.demo.repositories.*;
import com.bexarair.demo.services.SmsSender;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
public class AirQualityController {

//    @Autowired
//    private AQRestRepository aqRecordRepository;
//


    private HttpResponse<JsonNode> jsonNodeHttpResponse;
    private static final String currentURL = "http://www.airnowapi.org/aq/observation/zipCode/current/?format=application/json&zipCode=";
    private static final String forecastURL = "http://www.airnowapi.org/aq/forecast/zipCode/?format=application/json&zipCode=";
    private static final LocalDate tomorrowDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
    private static final String dateURL = "&date=" + tomorrowDate;
    private static final String distanceURL = "&distance=0&API_KEY=";
    private static final String apiKey = "C9616B67-963C-4F6A-A5B5-C81979B9FF27";
    private static final String[] zipcodes = {"78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"};
    private static final String[] testZip = {"78002", "78006", "78009", "78015", "78023"};
    private static final String oneZip = "78002";
    private static Map <String, HttpResponse> apiResponses = new HashMap<>();
//    private static long currentAirQualityID;
    private static long currentAirQualityID;
    private static String catNameOneHour;
    private static String dateObservedOneHour;
    private static int aqiOneHour;
    private static List<String> zippy = new ArrayList<>();

/*********************THIS IS INJECTION*******************/
    private final AirQualityRepository airCRUD;
    private final UserRepository userCRUD;
    private final LocationRepository locationCRUD;
    private final ForecastRepository forecastCRUD;
    private final SmsSender textAlerts;
    public AirQualityController(AirQualityRepository airCRUD, UserRepository userCRUD, LocationRepository locationCRUD, ForecastRepository forecastCRUD, SmsSender textAlerts){
        this.airCRUD = airCRUD;
        this.userCRUD = userCRUD;
        this.locationCRUD = locationCRUD;
        this.forecastCRUD = forecastCRUD;
        this.textAlerts = textAlerts;
    }
/**********************************************************/
//3600000

/********************Database Injection**********************/

    @Scheduled(cron = "0 0 7 * * ?") //grab at 7am everyday
    public void getAir() {
        try {

            for(int i = 0; i < zipcodes.length; i++) {
                jsonNodeHttpResponse = Unirest.get(currentURL + zipcodes[i] + distanceURL + apiKey)
                        .asJson();
                apiResponses.put(zipcodes[i], jsonNodeHttpResponse);

                JSONArray aqiArray = jsonNodeHttpResponse.getBody().getArray();
                JSONObject currentAir = aqiArray.getJSONObject(1);

                String dateObserved = currentAir.getString("DateObserved");
                int hour = currentAir.getInt("HourObserved");
                String localTimeZone = currentAir.getString("LocalTimeZone");
                String reportingArea = currentAir.getString("ReportingArea");
                String stateCode = currentAir.getString("StateCode");
                double latitude = currentAir.getDouble("Latitude");
                double longitude = currentAir.getDouble("Longitude");
                String parameterName = currentAir.getString("ParameterName");
                int AQI = currentAir.getInt("AQI");
                int number = currentAir.getJSONObject("Category").getInt("Number");
                String name = currentAir.getJSONObject("Category").getString("Name");


                    AirQualityRecord newAirQuality = new AirQualityRecord();
                    newAirQuality.setDateObserved(dateObserved);
                    newAirQuality.setHourObserved(hour);
                    newAirQuality.setLocalTimeZone(localTimeZone);
                    newAirQuality.setReportingArea(reportingArea);
                    newAirQuality.setStateCode(stateCode);
                    newAirQuality.setLatitude(latitude);
                    newAirQuality.setLongitude(longitude);
                    newAirQuality.setParameterName(parameterName);
                    newAirQuality.setAQI(AQI);
                    newAirQuality.setCategoryNumber(number);
                    newAirQuality.setCategoryName(name);
                    newAirQuality.setZipCode(zipcodes[i]);
                    airCRUD.save(newAirQuality);
            }

        } catch (UnirestException e) {
            e.printStackTrace();
            }




//        Date dt = new Date();
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//        String date = simpleDateFormat.format(dt);
//        System.out.println(date);
//
//
////        List<ForecastRecord> forecast = forecastCRUD.findAllByForecastDate(date);
//        List<AirQualityRecord> current = airCRUD.findAllByDateObserved(date);
//        List<UserLocation> useLocation = locationCRUD.findAllByTextAlert(true);
//        List<User> alertUsers = userCRUD.findAll();
//
////Used to send the Text Alerts
//        int count = 0;
//        for (int i=0; i < current.size(); i++) {
//            count++;
//            if(count > alertUsers.size()){
//                break;
//            }
//            String currentZipCode = current.get(i).getZipCode();
//            String currentCatName = current.get(i).getCategoryName();
//            for(int j=0; j < useLocation.size(); j++) {
//                long userLocationId = useLocation.get(j).getUser().getId();
//                String locationZipCode = useLocation.get(j).getZipcode();
//                for(int k=0; k < alertUsers.size(); k++){
//                    long userId = alertUsers.get(k).getId();
//                    if (userLocationId == userId && currentZipCode.equals(locationZipCode) && currentCatName.equals("Unhealthy for Sensitive Groups") || currentCatName.equals("Unhealthy") || currentCatName.equals("Very Unhealthy") || currentCatName.equals("Hazardous") ){
//                        textAlerts.currentAlert(current.get(j), useLocation.get(j), alertUsers.get(k));
////                    System.out.println("text message was sent");
//                    //this is putting the info into the text and then sending
//                    }
//                }
//            }
//        }

// look through all the airquality records...

        // users are opted in to text messages..

        // if they are... then what are their locations?

        // compare the user locations zip codes against all of the air quality records zip codes

            // if they match, and the air quality record has a bad AQI, then send a text message



//
// location zipCode to the current Zipcode/


    }//end of getAir

//    @Scheduled(cron = "0 0 14 * * ?")


    @Scheduled(cron = "0 0 0/1 * * ?") //runs at the top of every hour
    public void sendAlertText() {

        try{
            for(int i = 0; i < testZip.length; i++) {
                jsonNodeHttpResponse = Unirest.get(currentURL + zipcodes[i] + distanceURL + apiKey)
                        .asJson();
                apiResponses.put(zipcodes[i], jsonNodeHttpResponse);

                JSONArray aqiArrayOneHour = jsonNodeHttpResponse.getBody().getArray();
                JSONObject currentAirOneHour = aqiArrayOneHour.getJSONObject(1);


                dateObservedOneHour = currentAirOneHour.getString("DateObserved");
//                int hourOne = currentAirOneHour.getInt("HourObserved");
                aqiOneHour = currentAirOneHour.getInt("AQI");
                catNameOneHour = currentAirOneHour.getJSONObject("Category").getString("Name");
                zippy.add(zipcodes[i]);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        Date dt = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dt);
        System.out.println(date);


        //        List<ForecastRecord> forecast = forecastCRUD.findAllByForecastDate(date);
        List<AirQualityRecord> current = airCRUD.findAllByDateObserved(date);
        List<UserLocation> useLocation = locationCRUD.findAllByTextAlert(true);
        List<User> alertUsers = userCRUD.findAll();


        for (int j = 0; j < useLocation.size(); j++) {
            long userLocationId = useLocation.get(j).getUser().getId();
            String locationZipCode = useLocation.get(j).getZipcode();
            System.out.println("We are on this iteration for user location: " + j);

            for (int k = 0; k < alertUsers.size(); k++) {
                long userId = alertUsers.get(k).getId();
                System.out.println("LOCATION USER ID: " + userLocationId);
                System.out.println("USER ID: " + userId);
                if (userLocationId == userId && (zippy.contains(locationZipCode) && catNameOneHour.equals("Good") || catNameOneHour.equals("Moderate") || catNameOneHour.equals("Unhealthy for Sensitive Groups") || catNameOneHour.equals("Unhealthy") || catNameOneHour.equals("Very Unhealthy") || catNameOneHour.equals("Hazardous"))) {
                    textAlerts.alertOneHour(dateObservedOneHour, aqiOneHour, catNameOneHour, useLocation.get(j), alertUsers.get(k));
                    System.out.println("MESSAGE SENT");
//                    System.out.println("text message was sent");
                    //this is putting the info into the text and then sending
                }
            }
        }
    }
//} //current AQI Loop








    /**attempting to grab users specific locations to display on graph**/
    @GetMapping("/graphs")
    public String showUserAir(Model viewModel){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userCRUD.findOne(sessionUser.getId());
        long user = sessionUser.getId();
        UserLocation location = locationCRUD.findOne(user);

//        viewModel.addAttribute("air", airCRUD.findByZipCode(location));
        return "users/graph-test";
    }


}//end of Class





