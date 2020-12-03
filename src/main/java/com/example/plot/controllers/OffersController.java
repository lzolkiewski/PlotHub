package com.example.plot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OffersController {
    
    @RequestMapping("/offer")
    public String offerMapping(Model model){
        return "offer";
    }
}
