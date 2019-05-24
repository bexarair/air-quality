package com.bexarair.demo.repositories;

import com.bexarair.demo.models.ForecastRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ForecastRepository extends CrudRepository<ForecastRecord, Long> {
    ForecastRecord findByZipCode(String zipCode);
    ForecastRecord findByForecastDate(String date);
    List<ForecastRecord> findAllByDateIssue(String date);
    List<ForecastRecord> findAllByForecastDate(String date);
}
