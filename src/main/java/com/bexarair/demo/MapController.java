package com.bexarair.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MapController {

    @GetMapping("/")
    public String showHomePage(){
        return "fragments/index-template";
    }
}
