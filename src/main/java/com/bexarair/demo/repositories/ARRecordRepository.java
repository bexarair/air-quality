package com.bexarair.demo.repositories;

import com.bexarair.demo.models.AirQualityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ARRecordRepository extends JpaRepository <AirQualityRecord, Long>  {
    AirQualityRecord findByZipCode(String zipCode);
    AirQualityRecord findByHourObserved(String hour);
    AirQualityRecord findByZipCodeAndHourObserved(String zipCode, String HourObserved);
    AirQualityRecord findByZipCodeAndDateObserved(String zipCode, Date dateObserved);

}
