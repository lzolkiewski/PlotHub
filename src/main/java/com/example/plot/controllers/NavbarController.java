package com.example.plot.controllers;

import com.example.plot.jpa.Offer;
import com.example.plot.management.OffersFilter;
import com.example.plot.management.OffersSorter;
import com.example.plot.management.Planer;
import com.example.plot.services.DatabaseService;
import com.example.plot.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.util.List;

@Controller
public class NavbarController {
    @Autowired
    private OffersService offersService;
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping("/")
    public String homeMapping(Model model){
        return "home";
    }

    @RequestMapping("/offers")
    public String offersMapping(Model model, OffersFilter offersFilter, OffersSorter offersSorter, Planer planer){
        List<Offer> offers;
//        differentiate between offersFilter and planer
        if ((offersFilter.getBuilding()!=null && offersFilter.getBuilding())
                || (offersFilter.getFence()!=null && offersFilter.getFence())
                || offersFilter.getAreaFrom()!=null || offersFilter.getAreaTo()!=null
                || offersFilter.getPriceFrom()!=null || offersFilter.getPriceTo()!=null){
            offers = offersService.getOffers(offersFilter);
        }else {
            offers = databaseService.getPlanerOffers(planer);
        }
//        sorting offers
        if(offersSorter.getArea()!=null || offersSorter.getPrice()!=null){
            offers = offersSorter.sortOffers(offers);
        }
//        model all necessary attributes
        model.addAttribute("offers", offers);
        model.addAttribute("plotTypes", databaseService.getPlotTypes());
        model.addAttribute("surroundings", databaseService.getSurroundings());
        model.addAttribute("cities", databaseService.getCities());
        model.addAttribute("countries",  databaseService.getCountries());

        return "offers";
    }

    @RequestMapping("/about_us")
    public String aboutUsMapping(Model model){
        return "about_us";
    }
    
}
