package com.bexarair.demo.controllers;

import com.bexarair.demo.models.AirQualityRecord;
import com.bexarair.demo.models.CityHospitalRecord;
import com.bexarair.demo.models.User;
import com.bexarair.demo.models.UserLocation;
import com.bexarair.demo.repositories.AQRestRepository;
import com.bexarair.demo.repositories.HospitalRecordRepository;
import com.bexarair.demo.repositories.LocationRepository;
import com.bexarair.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private AQRestRepository aqRecordRepository;
    private UserRepository userCRUD;
    private LocationRepository locationCRUD;
    private HospitalRecordRepository hospitalRecordCRUD;


    public RestController(AQRestRepository aqRecordRepository, LocationRepository locationCRUD, UserRepository userCRUD, HospitalRecordRepository hospitalRecordCRUD) {
        this.aqRecordRepository = aqRecordRepository;
        this.userCRUD = userCRUD;
        this.locationCRUD = locationCRUD;
        this.hospitalRecordCRUD = hospitalRecordCRUD;
    }

    @GetMapping("/airquality/currentdate")
    @ResponseBody
    public List<AirQualityRecord> getAllAirQuality() {

        Date dt = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dt);


        return aqRecordRepository.findByDateObserved(date);
    }



        // this is pulling from the air quality table //
    @GetMapping("/airquality/zipcode/{zipcode}")
    @ResponseBody
    public ResponseEntity<List<AirQualityRecord>> getAirQualityByZip(@PathVariable String zipcode)
            throws ResourceNotFoundException {
        List <AirQualityRecord> airQualityRecord =
                aqRecordRepository
                        .findByZipCode(zipcode);
//                        .orElseThrow(() -> new ResourceNotFoundException("Zipcode not found on :: " + zipCode));
         return ResponseEntity.ok().body(airQualityRecord);
    }

    /**this returns back all the hospital records**/

    @GetMapping("/hospitalrecords")
    @ResponseBody
    public ResponseEntity<List<CityHospitalRecord>> getAllHospitalRecords()
            throws ResourceNotFoundException {
        List <CityHospitalRecord> cityHospitalRecord =
                hospitalRecordCRUD.findAll();
        return ResponseEntity.ok().body(cityHospitalRecord);
    }
    /**this filters out the locations of the user by the current date**/

    @GetMapping("/hospitalrecords/zipcode/{zipcode}")
    @ResponseBody
    public ResponseEntity<List<CityHospitalRecord>> getHospitalRecordsByZip(@PathVariable String zipcode)
            throws ResourceNotFoundException {
        List <CityHospitalRecord> cityHospitalRecord =
                hospitalRecordCRUD
                        .findByzipcode(zipcode);
        return ResponseEntity.ok().body(cityHospitalRecord);
    }

    /**this filters out the locations of the user by the current date**/

    @GetMapping("/airquality/user/{userId}")
    @ResponseBody
    public ResponseEntity<List<AirQualityRecord>> getAirQualityByZipAndUser(@PathVariable long userId)
            throws ResourceNotFoundException {

        Date dt = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dt);

        User user = userCRUD.findById(userId);
        List<UserLocation> userLocations = user.getLocation();
        List<AirQualityRecord> aqRecordToReturn = new ArrayList<>();
        List<AirQualityRecord> airQualityRecords = aqRecordRepository.findByDateObserved(date);

        for (int i = 0; i < userLocations.size(); i++) {

            UserLocation userLocation = userLocations.get(i);
            for (int j = 0; j < airQualityRecords.size(); j++) {

                if ((userLocation.getZipcode().equals(airQualityRecords.get(j).getZipCode()))) {

                            System.out.println("userLocations loop #: " + i );
                            System.out.println("airQualityRecords loop #: " + j );
                            aqRecordToReturn.add(airQualityRecords.get(j));

                }
            }
//                        .orElseThrow(() -> new ResourceNotFoundException("Zipcode not found on :: " + zipCode));
        }
                return ResponseEntity.ok().body(aqRecordToReturn);

    }

    /** James' garbage **/

//    @GetMapping("/locations/user/{userId}")
//    @ResponseBody
//    public ResponseEntity<List<UserLocation>> getLocationsByUser(@PathVariable long userId)
//            throws ResourceNotFoundException {
//        // does the userid in the @getMapping request need to be changed into String format?
//
////        User user = userCRUD.findById(userId);
////        List<UserLocation> userLocations = user.getLocation();
//        List<UserLocation> locationsToReturn = new ArrayList<>();
//        List<UserLocation> locations = locationCRUD.findAll();
////        List<AirQualityRecord> airQualityRecords = aqRecordRepository.findAll();
//
//        for (int i = 0; i < locations.size(); i++) {
//
////            UserLocation userLocation = userLocations.get(i);
////            for (int j = 0; j < locations.size(); j++) {
//            System.out.println("This is out of the if : " + locations.get(i).getUser().getId());
//            if (userId == locations.get(i).getUser().getId()) {
//
//                System.out.println("location userId: " + locations.get(i).getUser().getId());
//                System.out.println("THE location " + locations);
//
////                    System.out.println("userLocations loop #: " + i );
////                    System.out.println("airQualityRecords loop #: " + i);
//                locationsToReturn.add(locations.get(i));
//
//            }
//        }
////                        .orElseThrow(() -> new ResourceNotFoundException("Zipcode not found on :: " + zipCode));
////        }
//        return ResponseEntity.ok().body(locationsToReturn);
//
//    }





    @GetMapping("/locations/user/{userId}")
    @ResponseBody
    public ResponseEntity<List<UserLocation>> getLocationsByUser(@PathVariable long userId)
            throws ResourceNotFoundException {
        // does the userid in the @getMapping request need to be changed into String format?

//        User user = userCRUD.findById(userId);
//        List<UserLocation> userLocations = user.getLocation();
        List<UserLocation> locationsToReturn = new ArrayList<>();
        List<UserLocation> locations = locationCRUD.findAll();
//        List<AirQualityRecord> airQualityRecords = aqRecordRepository.findAll();

        for (int i = 0; i < locations.size(); i++) {

//            UserLocation userLocation = userLocations.get(i);
//            for (int j = 0; j < locations.size(); j++) {
            System.out.println("This is out of the if : " + locations.get(i).getUser().getId());
            if (userId == locations.get(i).getUser().getId()) {

                    System.out.println("location userId: " + locations.get(i).getUser().getId());
                    System.out.println("THE location " + locations);

//                    System.out.println("userLocations loop #: " + i );
//                    System.out.println("airQualityRecords loop #: " + i);
                    locationsToReturn.add(locations.get(i));

                }
            }
//                        .orElseThrow(() -> new ResourceNotFoundException("Zipcode not found on :: " + zipCode));
//        }
        return ResponseEntity.ok().body(locationsToReturn);

    }







//    @PostMapping("/airquality")
//    public AirQualityRecord createAirQuality(@Valid @RequestBody AirQualityRecord airQualityRecord) {
//        return aqRecordRepository.save(airQualityRecord);
//    }
//
//    @PutMapping("/airquality/{userId}/{zipcode}")
//    public ResponseEntity<AirQualityRecord> updateAirQuality(
//            @PathVariable(value = "id") Long userId, @Valid @RequestBody AirQualityRecord airRecordDetails, @PathVariable String zipCode)
//            throws ResourceNotFoundException {
//        AirQualityRecord airQualityRecord =
//                aqRecordRepository
//                        .findByZipCodeAndDateObserved(zipCode, new Date());
////                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));
//        airQualityRecord.setAQI(airRecordDetails.getAQI());
//        airQualityRecord.setZipCode(airRecordDetails.getZipCode());
//        airQualityRecord.setDateObserved(airRecordDetails.getDateObserved());
////        airQualityRecord.setUpdatedAt(new Date());  idk what this is.
//        final AirQualityRecord updatedAirQuality = aqRecordRepository.save(airRecordDetails);
//        return ResponseEntity.ok(updatedAirQuality);
//    }
//
//
//
//    @DeleteMapping("/airquality/{userId}/{zipcode}")
//    public Map<String, Boolean> deleteAirQualityRecord(@PathVariable(value = "id") Long userId, @PathVariable String zipCode) throws Exception {
//        AirQualityRecord airQualityRecord =
//                aqRecordRepository
//                        .findByZipCodeAndDateObserved(zipCode, new Date());
////                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + zipCode));
//        aqRecordRepository.delete(airQualityRecord);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }
/*********************END OF REST API*******************/

}



// loop through all forecasts back..
// loop through all userlocations.

// inside the loop..

// loop through userlocations if any of the zipcodes match any of the users, then send them the forecast back as a list

// grab all users that have opted into the daily alert

// filter it by user id... then we are looking at the individual user.  then we need to send them a text of their userLocations according to the forecast...