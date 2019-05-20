package com.bexarair.demo.controllers;

import com.bexarair.demo.models.AirQualityRecord;
import com.bexarair.demo.models.User;
import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.repositories.AirQualityRepository;
import com.bexarair.demo.repositories.LocationRepository;
import com.bexarair.demo.repositories.UserRepository;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.swing.Timer;

@Controller
public class AirQualityController {


    private HttpResponse<JsonNode> jsonNodeHttpResponse;
    private static final String currentURL = "http://www.airnowapi.org/aq/observation/zipCode/current/?format=application/json&zipCode=";
    private static final String forecastURL = "http://www.airnowapi.org/aq/forecast/zipCode/?format=application/json&zipCode=";
    private static final LocalDate tomorrowDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
    private static final String dateURL = "&date=" + tomorrowDate;
    private static final String distanceURL = "&distance=0&API_KEY=";


    private static final String apiKey = "A4D00993-8E59-4B13-924E-9BA79D1FCE63";
    private static final String[] zipcodes = {"78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"};
    private static final String[] testZip = {"78002", "78006", "78009", "78015", "78023"};
    private static Map <String, HttpResponse> apiResponses = new HashMap<>();
//    private static long currentAirQualityID;
    private static long currentAirQualityID;

/*********************THIS IS INJECTION*******************/
    private final AirQualityRepository airCRUD;
    private final UserRepository userCRUD;
    private final LocationRepository locationCRUD;
    public AirQualityController(AirQualityRepository airCRUD, UserRepository userCRUD, LocationRepository locationCRUD){
        this.airCRUD = airCRUD;
        this.userCRUD = userCRUD;
        this.locationCRUD = locationCRUD;
    }
/**********************************************************/
//3600000


//    @Scheduled(fixedRate = 10000) //grabs air every hour
    public void getAir() {
        try {

        for(int i = 0; i < testZip.length; i++) {
            jsonNodeHttpResponse = Unirest.get(currentURL + testZip[i] + distanceURL + apiKey)
                    .asJson();
            apiResponses.put(testZip[i], jsonNodeHttpResponse);

            JSONArray aqiArray = jsonNodeHttpResponse.getBody().getArray();
            JSONObject currentAir = aqiArray.getJSONObject(0);

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



            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateObserved);

                AirQualityRecord newAirQuality = new AirQualityRecord();
                newAirQuality.setDateObserved(date);
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
                newAirQuality.setZipCode(testZip[i]);
                airCRUD.save(newAirQuality);

                //this is pulling the id but only the last one.  Need to store them all
                currentAirQualityID = newAirQuality.getId();
                System.out.println("This is in the loop " + currentAirQualityID);
            } catch (ParseException parseException) {
                System.out.println(parseException);
            }


        }

        } catch (UnirestException e) {
            e.printStackTrace();
        }




    }//end of getAir
////3600000
////@Scheduled(fixedRate = 10000)
//    public void getFutureAir(){
//        try {
//
//            for(int i = 0; i < testZip.length; i++) {
//                jsonNodeHttpResponse = Unirest.get(forecastURL + testZip[i] + dateURL + distanceURL + apiKey)
//                        .asJson();
//                apiResponses.put(testZip[i], jsonNodeHttpResponse);
//
//                JSONArray aqiArray = jsonNodeHttpResponse.getBody().getArray();
//                JSONObject forecastAir = aqiArray.getJSONObject(0);
//
//                System.out.println(tomorrowDate);
//
////                String dateIssue = forecastAir.getString("DateIssue");
//                String dateForcast = forecastAir.getString("DateForecast");
//                String forcastParameterName = forecastAir.getString("ParameterName");
//                int forecastAqi = forecastAir.getInt("AQI");
//                int forecastNumber = forecastAir.getJSONObject("Category").getInt("Number");
//                String forecastName = forecastAir.getJSONObject("Category").getString("Name");
//                boolean actionDay = forecastAir.getBoolean("ActionDay");
//                String discussion = forecastAir.getString("Discussion");
//
//
//
//
//
//                Date forcastDate = null;
//                try {
//                    forcastDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateForcast);
//                    System.out.println("this is in the forecast loop " + currentAirQualityID);
////                    AirQualityRecord newAirQuality = new AirQualityRecord();
//                    //trying to find record by zipcode -- but more than one zipcode
////                    AirQualityRecord currentRecord = airCRUD.findByZipCode(testZip[i]);
//                    AirQualityRecord currentRecord = airCRUD.findByZipCodeAndHourObserved(testZip[i], "7");
//
//                    //need to iterate through the list of IDs
////                    AirQualityRecord currentRecord = airCRUD.findOne(currentAirQualityID[i]);
////                    newAirQuality.setId(currentAirQualityID);
////                    newAirQuality.setCategoryName();
//                    currentRecord.setForecastDate(forcastDate);
//                    currentRecord.setForecastParameterName(forcastParameterName);
//                    currentRecord.setForecastAQI(forecastAqi);
//                    currentRecord.setForecastCategoryNumber(forecastNumber);
//                    currentRecord.setForecastCategoryName(forecastName);
//                    currentRecord.setForecastActionDay(actionDay);
//                    currentRecord.setForecastDiscussion(discussion);
//                    airCRUD.save(currentRecord);
//
//
//                } catch (ParseException parseException) {
//                    System.out.println(parseException);
//                }
//
//
//            }
//
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }
//    }//end of future air



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





