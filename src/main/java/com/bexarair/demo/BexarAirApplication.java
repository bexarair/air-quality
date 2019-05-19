package com.bexarair.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BexarAirApplication {

    public static void main(String[] args) {
        SpringApplication.run(BexarAirApplication.class, args);
    }

}
