package com.bexarair.demo.repositories;

import com.bexarair.demo.models.ForecastRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends CrudRepository<ForecastRecord, Long> {
    ForecastRecord findByZipCode(String zipCode);

}
