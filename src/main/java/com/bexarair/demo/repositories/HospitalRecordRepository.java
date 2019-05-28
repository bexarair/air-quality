package com.bexarair.demo.repositories;

import com.bexarair.demo.models.AirQualityRecord;
import com.bexarair.demo.models.CityHospitalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalRecordRepository extends JpaRepository<CityHospitalRecord, Long> {
    List<CityHospitalRecord> findByzipcode(String zipCode);
}
