package com.example.plot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/account")
    public String accountMapping(Model model){
        // if user is logged redirect him to accont 
        return "account";
    }
    @RequestMapping("/login")
    public String loginMapping(Model model){
        // if user is not logged first redirect him to login / sign-up form then account
        return "login";
    }
    @RequestMapping("/help")
    public String helpMapping(Model model){
        return "help";
    }
    
}