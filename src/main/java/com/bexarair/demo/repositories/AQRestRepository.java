package com.bexarair.demo.repositories;

import com.bexarair.demo.models.AirQualityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AQRestRepository extends JpaRepository <AirQualityRecord, Long>  {
    List<AirQualityRecord> findByZipCode(String zipCode);
    AirQualityRecord findByHourObserved(String hour);
    AirQualityRecord findByZipCodeAndHourObserved(String zipCode, String HourObserved);
    AirQualityRecord findByZipCodeAndDateObserved(String zipCode, Date dateObserved);

}
