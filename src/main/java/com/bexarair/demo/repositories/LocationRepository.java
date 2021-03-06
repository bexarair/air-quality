package com.bexarair.demo.repositories;

import com.bexarair.demo.models.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<UserLocation, Long> {
    List<UserLocation> findAllByUserId(long userId);
    List<UserLocation> findAll();

    List<UserLocation> findAllByTextAlert(boolean alert);
    List<UserLocation> findAllByDailyAlert(boolean alert);

}
