package com.bexarair.demo.repositories;

import com.bexarair.demo.models.AirQualityRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface AirQualityRepository extends CrudRepository<AirQualityRecord, Long> {
    AirQualityRecord findByZipCode(String zipCode);


}
