package com.bexarair.demo.repositories;

import com.bexarair.demo.models.AirQualityRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Repository
public interface AirQualityRepository extends CrudRepository<AirQualityRecord, Long> {
    AirQualityRecord findByZipCode(String zipCode);
AirQualityRecord findByHourObserved(String hour);
AirQualityRecord findByZipCodeAndHourObserved(String zipCode, String HourObserved);
AirQualityRecord findByZipCodeAndDateObserved(String zipCode, Date dateObserved);



}
