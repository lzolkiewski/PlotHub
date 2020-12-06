package com.example.plot.controllers;

import com.example.plot.jpa.Offer;
import com.example.plot.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {
    @Autowired
    private OffersService offersService;

    @GetMapping("/offer/{id}")
    public String offerMapping(Model model, @PathVariable("id")Integer id){
        Offer offer = offersService.getOffer(id);

        model.addAttribute("offer", offer);

        return "offer";
    }
//    dodać podgląd oferty / edycję oferty /
}
