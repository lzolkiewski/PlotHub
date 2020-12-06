package com.example.plot.controllers;

import com.example.plot.jpa.Offer;
import com.example.plot.management.OffersFilter;
import com.example.plot.management.OffersSorter;
import com.example.plot.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NavbarController {
    @Autowired
    private OffersService offersService;

    @RequestMapping("/")
    public String homeMapping(Model model){
        return "home";
    }

    @RequestMapping("/offers")
    public String offersMapping(Model model, OffersFilter offersFilter, OffersSorter offersSorter){
        List<Offer> offers = offersService.getOffers();

        if(offersSorter.getArea()!=null || offersSorter.getPrice()!=null){
            offers = offersService.getOffersInOrder(offersSorter);
        }

        model.addAttribute("offers", offers);
        
        return "offers";
    }

    @RequestMapping("/about_us")
    public String aboutUsMapping(Model model){
        return "about_us";
    }
    
}
