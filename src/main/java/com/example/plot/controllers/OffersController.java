package com.example.plot.controllers;

import com.example.plot.jpa.Offer;
import com.example.plot.management.OffersFilter;
import com.example.plot.management.Planer;
import com.example.plot.services.DatabaseService;
import com.example.plot.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OffersController {
    @Autowired
    private OffersService offersService;
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping("/offers")
    public String offersMapping(Model model, OffersFilter offersFilter, Planer planer) {
        List<Offer> offers;
//        differentiate between offersFilter and planer
        if (offersFilter.checkTheNeedToFindBuilding() || offersFilter.checkTheNeedToFindFence()
                || offersFilter.getPlotTypeId() != null || offersFilter.getSurroundingId() != null
                || offersFilter.getAreaFrom() != null || offersFilter.getAreaTo() != null
                || offersFilter.getPriceFrom() != null || offersFilter.getPriceTo() != null) {
            offers = offersService.getFilteredOffers(offersFilter);
        } else {
            offers = offersService.getPlanerOffers(planer);
        }

//        model all necessary attributes
        model.addAttribute("expected", planer.getSurface());
        model.addAttribute("offers", offers);
        model.addAttribute("plotTypes", databaseService.getPlotTypes());
        model.addAttribute("surroundings", databaseService.getSurroundings());
        model.addAttribute("cities", databaseService.getCities());
        model.addAttribute("countries",  databaseService.getCountries());

        return "offers";
    }

    @GetMapping("/offer/{id}")
    public String offerMapping(Model model, @PathVariable("id")Integer id) {
        model.addAttribute("offer", offersService.getOfferById(id));
        model.addAttribute("email", databaseService.getUserEmail(id));
        
        model.addAttribute("date", databaseService.getUserOffersById(id));
        return "offer";
    }

}
