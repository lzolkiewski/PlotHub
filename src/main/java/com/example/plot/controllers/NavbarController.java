package com.example.plot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavbarController {
    
    @RequestMapping("/")
    public String homeMapping(Model model){
        return "home";
    }

    @RequestMapping("/offers")
    public String offersMapping(Model model){
        return "offers";
    }

    @RequestMapping("/about_us")
    public String aboutUsMapping(Model model){
        return "about_us";
    }
    
}
