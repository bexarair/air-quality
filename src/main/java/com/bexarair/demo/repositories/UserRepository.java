package com.bexarair.demo.repositories;

import com.bexarair.demo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    List<User> findAll();
    List<User> findAllByGodMode(boolean godMode);



//    List<User> findAllByDailyAlert(boolean alert);


}
