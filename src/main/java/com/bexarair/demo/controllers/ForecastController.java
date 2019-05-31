package com.bexarair.demo.controllers;

import com.bexarair.demo.models.AirQualityRecord;
import com.bexarair.demo.models.ForecastRecord;
import com.bexarair.demo.models.User;
import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.repositories.AirQualityRepository;
import com.bexarair.demo.repositories.ForecastRepository;
import com.bexarair.demo.repositories.LocationRepository;
import com.bexarair.demo.repositories.UserRepository;
import com.bexarair.demo.services.SmsSender;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
//import javafx.util.converter.LocalDateStringConverter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Controller
public class ForecastController {

    private HttpResponse<JsonNode> jsonNodeHttpResponse;
    private static final String currentURL = "http://www.airnowapi.org/aq/observation/zipCode/current/?format=application/json&zipCode=";
    private static final String forecastURL = "http://www.airnowapi.org/aq/forecast/zipCode/?format=application/json&zipCode=";
    private static final LocalDate tomorrowDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
    private static final String dateURL = "&date=" + tomorrowDate;
    private static final String distanceURL = "&distance=0&API_KEY=";


    private static final String apiKey = "A4D00993-8E59-4B13-924E-9BA79D1FCE63";
    private static final String[] zipcodes = {"78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"};
//    private static final String[] testZip = {"78002", "78006", "78009", "78015", "78023", "75001"};
    private static Map<String, HttpResponse> apiResponses = new HashMap<>();
    //    private static long currentAirQualityID;
    private static long currentForecastAQID;
    private static boolean seeder = false;

    /*********************THIS IS INJECTION*******************/
    private final ForecastRepository forecastCRUD;
    private final AirQualityRepository airCRUD;
    private final UserRepository userCRUD;
    private final LocationRepository locationCRUD;
    private SmsSender textAlerts;
    public ForecastController(ForecastRepository forecastCRUD, AirQualityRepository airCRUD, UserRepository userCRUD, LocationRepository locationCRUD, SmsSender textAlerts){
        this.forecastCRUD = forecastCRUD;
        this.airCRUD = airCRUD;
        this.userCRUD = userCRUD;
        this.locationCRUD = locationCRUD;
        this.textAlerts = textAlerts;
    }
    /**********************************************************/



    @Scheduled(cron = "0 0 7 * * ? ")
//    @Scheduled(fixedRate = 60000)
    public void setupFutureAir(){
        try {

            for(int i = 0; i < zipcodes.length; i++) {
                jsonNodeHttpResponse = Unirest.get(forecastURL + zipcodes[i] + dateURL + distanceURL + apiKey)
                        .asJson();
                apiResponses.put(zipcodes[i], jsonNodeHttpResponse);

                JSONArray aqiArray = jsonNodeHttpResponse.getBody().getArray();
                JSONObject forecastAir = aqiArray.getJSONObject(1);

                System.out.println(tomorrowDate);

                System.out.println(aqiArray);
                System.out.println(forecastAir);
                System.out.println(jsonNodeHttpResponse);

                String dateIssue = forecastAir.getString("DateIssue");
                String reportingArea = forecastAir.getString("ReportingArea");
                String stateCode = forecastAir.getString("StateCode");
                double latitude = forecastAir.getDouble("Latitude");
                double longitude = forecastAir.getDouble("Longitude");
                String dateForcast = forecastAir.getString("DateForecast");
                String forcastParameterName = forecastAir.getString("ParameterName");


                int forecastAqi = forecastAir.getInt("AQI");


                int forecastNumber = forecastAir.getJSONObject("Category").getInt("Number");
                String forecastName = forecastAir.getJSONObject("Category").getString("Name");
                boolean actionDay = forecastAir.getBoolean("ActionDay");
                String discussion = forecastAir.getString("Discussion");





                Date forcastDate = null;
                Date forecastDateIssue = null;
                try {

//                    forcastDate = LocalDate.parse(dateForcast);

                    forcastDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateForcast);
//                    forecastDateIssue = new SimpleDateFormat("yyyy-MM-dd").parse(dateIssue);
                    System.out.println("this is in the forecast loop " + currentForecastAQID);

                    ForecastRecord newForecastRecord = new ForecastRecord();
                    newForecastRecord.setDateIssue(dateIssue);
                    newForecastRecord.setReportingArea(reportingArea);
                    newForecastRecord.setStateCode(stateCode);
                    newForecastRecord.setLatitude(latitude);
                    newForecastRecord.setLongitude(longitude);
                    newForecastRecord.setForecastDate(dateForcast);
                    newForecastRecord.setParameterName(forcastParameterName);


                    newForecastRecord.setAQI(forecastAqi);


                    newForecastRecord.setCategoryNumber(forecastNumber);
                    newForecastRecord.setCategoryName(forecastName);
                    newForecastRecord.setActionDay(actionDay);
                    newForecastRecord.setDiscussion(discussion);
                    newForecastRecord.setZipCode(zipcodes[i]);
                    forecastCRUD.save(newForecastRecord);



                } catch (ParseException parseException) {
                    System.out.println(parseException);
                }


            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }




    }//end of future air


    @Scheduled(cron = "0 0 18 * * ?")
    public void sendDailyText() {
    Date dt = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(dt);
    c.add(Calendar.DATE, 1);
    dt = c.getTime();

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    String date = simpleDateFormat.format(dt);
    System.out.println(date);


    List<ForecastRecord> forecast = forecastCRUD.findAllByForecastDate(date);
    List<UserLocation> useLocation = locationCRUD.findAllByTextAlert(true);
    List<User> alertUsers = userCRUD.findAll();



//                text messages based alerts and ID
    for (int i = 0; i < useLocation.size(); i++) {
        long userId = useLocation.get(i).getUser().getId();
        for (int j = 0; j < alertUsers.size(); j++) {
            long userLocationId = alertUsers.get(j).getId();
            System.out.println("LOCATION USER ID: " + useLocation.get(i).getUser().getId());
            System.out.println("USER ID : " + alertUsers.get(j).getId());
            if (userLocationId == userId) {
                textAlerts.aqiOverviewText(forecast.get(i), useLocation.get(i), alertUsers.get(j));
                //this is putting the info into the text and then sending
//
            }
        }
    }
}




}
