//package com.bexarair.demo.controllers;
//
//
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import org.json.JSONObject;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class AirQualityController {
//    private HttpResponse<JsonNode> jsonNodeHttpResponse;
//    private static final String baseURL = "http://www.airnowapi.org/aq/observation/zipCode/current/?format=application/json&zipCode=";
//    private static final String partTwoURL = "&distance=0&API_KEY=";
//    private static final String apiKey = "C9616B67-963C-4F6A-A5B5-C81979B9FF27";
//    private static final String[] zipcodes = {"78002","78006","78009","78015","78023","78039","78052","78056","78063","78064","78065","78066","78069","78073","78101","78108","78109","78112","78114","78121","78124","78148","78150","78152","78154","78155","78163","78201","78202","78203","78204","78205","78207","78208","78209","78210","78211","78212","78213","78214","78215","78216","78217","78218","78219","78220","78221","78222","78223","78224","78225","78226","78227","78228","78229","78230","78231","78232","78233","78234","78235","78236","78237","78238","78239","78240","78242","78244","78245","78247","78248","78249","78250","78251","78252","78253","78254","78255","78256","78257","78258","78259","78260","78261","78263","78264","78266"};
//
//    // need to put on a time interval to ping every hour.
//    @GetMapping("/")
//    public String getAir() {
//        //bunch of code that isn't relevant to the api call...//
//        //here's the api call...//
//        try {
//
//        for(int i = 0; i < zipcodes.length; i++) {
//
//            jsonNodeHttpResponse = Unirest.get(baseURL + zipcodes[i] + partTwoURL + apiKey)
////                    .routeParam("movieId", movieUrlLongId.toString())
//                    .asJson();
//        }
//
//        } catch (UnirestException e) {
//            e.printStackTrace();
//        }
//
//        JSONObject aqiObject = jsonNodeHttpResponse.getBody().getObject();
//        //i converted the json node response into a json object and im pulling the id off that object by using the .getInt method//
////        Integer movieIdInt = movieObject.getInt("id");
//
//        System.out.println(jsonNodeHttpResponse);
//        return jsonNodeHttpResponse;
//    }
//}
//
//



