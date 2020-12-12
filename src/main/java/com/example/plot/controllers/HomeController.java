package com.example.plot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homeMapping(){
        return "home";
    }

    @RequestMapping("/help")
    public String helpMapping(){
        return "help";
    }
    
}