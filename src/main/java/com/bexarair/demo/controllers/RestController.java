package com.bexarair.demo.controllers;

import com.bexarair.demo.models.AirQualityRecord;
import com.bexarair.demo.repositories.AQRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Controller
public class RestController {
    @Autowired
    private AQRestRepository aqRecordRepository;


    @GetMapping("/airquality")
    @ResponseBody
    public List<AirQualityRecord> getAllAirQuality() {
        return aqRecordRepository.findAll();    }


    @GetMapping("/airquality/{zipcode}")
    @ResponseBody
    public ResponseEntity<List<AirQualityRecord>> getAirQualityByZip(@PathVariable String zipcode)
            throws ResourceNotFoundException {
        List <AirQualityRecord> airQualityRecord =
                aqRecordRepository
                        .findByZipCode(zipcode);  /// what ?

//                        .orElseThrow(() -> new ResourceNotFoundException("Zipcode not found on :: " + zipCode));
//        List<AirQualityRecord> returnedList = new ArrayList<>();
//        returnedList.add(airQualityRecord);
         return ResponseEntity.ok().body(airQualityRecord);
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
    }
/*********************END OF REST API*******************/

//}



// loop through all forecasts back..
// loop through all userlocations.

// inside the loop..

// loop through userlocations if any of the zipcodes match any of the users, then send them the forecast back as a list

// grab all users that have opted into the daily alert

// filter it by user id... then we are looking at the individual user.  then we need to send them a text of their userLocations according to the forecast...