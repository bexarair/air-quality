package com.bexarair.demo;

import com.bexarair.demo.controllers.FileController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class BexarAirApplication {

    public static void main(String[] args) throws IOException {
        new File(FileController.uploadingDir).mkdirs();
        SpringApplication.run(BexarAirApplication.class, args);
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("user.dir: " + currentDirectory);
    }


}
