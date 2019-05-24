package com.bexarair.demo.repositories;

import com.bexarair.demo.models.AirQualityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AQRestRepository extends JpaRepository <AirQualityRecord, Long>  {
    List<AirQualityRecord> findByZipCode(String zipCode);
    List<AirQualityRecord> findByHourObserved(String hour);
    List<AirQualityRecord> findByZipCodeAndHourObserved(String zipCode, String HourObserved);
    List<AirQualityRecord> findByZipCodeAndDateObserved(String zipCode, Date dateObserved);

        @Query(value = "SELECT AQI FROM air_quality " +
                "WHERE date_observed >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY " +
                "AND date_observed < curdate() - INTERVAL DAYOFWEEK(curdate())-1 DAY", nativeQuery = true)
        AirQualityRecord findByWeek();
}
