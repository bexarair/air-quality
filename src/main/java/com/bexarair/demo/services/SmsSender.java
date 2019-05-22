package com.bexarair.demo.services;
// Install the Java helper library from twilio.com/docs/libraries/java
import com.bexarair.demo.models.AirQualityRecord;
import com.bexarair.demo.models.ForecastRecord;
import com.bexarair.demo.models.User;
import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.repositories.ForecastRepository;
import com.bexarair.demo.repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service("SmsSender")
public class SmsSender {

    @Autowired
    public ForecastRepository forecastCRUD;
    public UserRepository userCRUD;
    public UserLocation locationCRUD;



    // Find your Account Sid and Auth Token at twilio.com/console
//    public static final String ACCOUNT_SID =
//            "ACcdf95a958c8700fb8b990660e446cc44";
//    public static final String AUTH_TOKEN =
//            "0f9e6ce4e07b1e526c73103d84afa9dd";
@Value("${twilio.sid}")
private String sid;
@Value("${twilio.token}")
private String token;



    public void aqiOverviewText(ForecastRecord record, UserLocation locationTitle, User userPhone) {
        Twilio.init(sid, token);
        int forecastAqi = record.getAQI();
        String catName = record.getCategoryName();
        String userTitle = locationTitle.getTitle();
        String forecastDate = record.getForecastDate().replace("00:00:00.0", "");
//        forecastDate.replace("00:00:00.0", "");
        String phone = userPhone.getPhone();

        Message message = Message.creator(new PhoneNumber("+1" + phone), // to
                        new PhoneNumber("+12103617392"), // from
                        "Forecast for " + forecastDate + " for "  + userTitle + " is showing an AQI of " + forecastAqi + " this falls in the " + catName + " category." )
                .create();

        System.out.println(message.getSid());
    }

    public void currentAlert(AirQualityRecord record, UserLocation locationTitle) {
        Twilio.init(sid, token);
        int currentAqi = record.getAQI();
        String catName = record.getCategoryName();
        String userTitle = locationTitle.getTitle();
        Date currentDate = record.getDateObserved();
//        user.getPhone();
        Message message = Message.creator(new PhoneNumber("+12106189890"), // to
                new PhoneNumber("+12103617392"), // from
                "***Warning*** /n Current AQI for " + currentDate + " for "  + userTitle + "is showing an AQI of " + currentAqi + " this falls in the " + catName + " category." )
                .create();

        System.out.println(message.getSid());
    }


}