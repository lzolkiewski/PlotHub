package com.example.plot.controllers;

import com.example.plot.jpa.Offer;
import com.example.plot.jpa.Surrounding;
import com.example.plot.jpa.offer.PlotType;
import com.example.plot.jpa.offer.address.City;
import com.example.plot.jpa.offer.address.Country;
import com.example.plot.management.OffersFilter;
import com.example.plot.management.OffersSorter;
import com.example.plot.management.Planer;
import com.example.plot.services.DatabaseService;
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
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping("/")
    public String homeMapping(Model model){
        return "home";
    }

    @RequestMapping("/offers")
    public String offersMapping(Model model, OffersFilter offersFilter, OffersSorter offersSorter, Planer planer){
        List<Offer> offers = offersService.getOffers();
        List<PlotType> plotTypes = databaseService.getPlotTypes();
        List<City> cities = databaseService.getCities();
        List<Country> countries = databaseService.getCountries();
        List<Surrounding> surroundings = databaseService.getSurroundings();

        if(offersSorter.getArea()!=null || offersSorter.getPrice()!=null){
            offers = offersService.getOffersInOrder(offersSorter);
        }


        model.addAttribute("offers", offers);
        model.addAttribute("plotTypes", plotTypes);
        model.addAttribute("surroundings", surroundings);
        model.addAttribute("cities", cities);
        model.addAttribute("countries", countries);

        return "offers";
    }

    @RequestMapping("/about_us")
    public String aboutUsMapping(Model model){
        return "about_us";
    }
    
}
