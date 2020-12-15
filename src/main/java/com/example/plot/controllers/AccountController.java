package com.example.plot.controllers;

import com.example.plot.jpa.User;
import com.example.plot.management.ChangePassword;
import com.example.plot.services.OffersService;
import com.example.plot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.plot.jpa.Offer;
import com.example.plot.services.DatabaseService;

@Controller
public class AccountController {
    @Autowired
    DatabaseService databaseService;
    @Autowired
    OffersService offersService;
    @Autowired
    UserService userService;

    String err;

    @RequestMapping("/account")
    public String accountMapping(Model model, HttpServletRequest request) {
        if ( request.getSession().getAttribute("user") == null ){
            return "redirect:/login";
        }

        return "account";
    }

    @GetMapping("/new_offer")
    public String newOfferMapping(Model model, Offer offer, HttpServletRequest request){
        if ( request.getSession().getAttribute("user") == null ){
            return "redirect:/login";
        }

        model.addAttribute("offer", offer);
        model.addAttribute("plotTypes", databaseService.getPlotTypes());
        model.addAttribute("driveTypes", databaseService.getDriveTypes());
        model.addAttribute("surroundings", databaseService.getSurroundings());
        model.addAttribute("streets", databaseService.getStreets());
        model.addAttribute("cities", databaseService.getCities());
        model.addAttribute("countries", databaseService.getCountries());
        model.addAttribute("er", false);

        return "new_offer";
    }

    @PostMapping("/new_offer")
    public String saveNewOffer(Model model, @Valid Offer offer, BindingResult bindingResult, HttpServletRequest request){
        if ( request.getSession().getAttribute("user") == null ){
            return "redirect:/login";
        }
//        if some errors remain on the same page
        if ( bindingResult.hasErrors() && offersService.addressHasErrors(offer) ) {
            model.addAttribute("offer", offer);
            model.addAttribute("plotTypes", databaseService.getPlotTypes());
            model.addAttribute("driveTypes", databaseService.getDriveTypes());
            model.addAttribute("surroundings", databaseService.getSurroundings());
            model.addAttribute("streets", databaseService.getStreets());
            model.addAttribute("cities", databaseService.getCities());
            model.addAttribute("countries", databaseService.getCountries());
            model.addAttribute("er", offersService.addressHasErrors(offer));

            return "new_offer";
        }
//        get address with id or add the address to database
        offer.setAddress(databaseService.addAddressIfNotExist(offer.getAddress()));
//        add offer
        offer = offersService.addOffer(offer);
//        pass logged user and newly created offer
        offersService.linkUserWithOffer(offer, (User) request.getSession().getAttribute("user"));
//        view the offer
        return "redirect:/offer/" + offer.getId();
    }

    @RequestMapping("/change_passwd")
    public String changePasswordMapping(Model model, HttpServletRequest request, ChangePassword changePassword){
        if ( request.getSession().getAttribute("user") == null ){
            return "redirect:/login";
        }

        model.addAttribute("changePassword", changePassword);
        model.addAttribute("err", err);

        return "change_passwd";
    }

    @PostMapping("/change_passwd/change")
    public String changePassword(Model model, HttpServletRequest request, ChangePassword changePassword) {
        if ( request.getSession().getAttribute("user") == null ){
            return "redirect:/login";
        }

        User user = (User) request.getSession().getAttribute("user");

        if ( user.getPassword().compareTo(changePassword.getCurrentPassword()) == 0 ){
            if ( changePassword.getNewPassword().compareTo(changePassword.getReNewPassword()) == 0 ){
//                remove message
                err = null;
//                set new password
                user.setPassword(changePassword.getNewPassword());
//                update database
                user = userService.updateUser(user);
//                set new logged user
                request.getSession().setAttribute("user", user);

                return "redirect:/account";
            } else {
//                not matching new passwords
                err = new String("Wprowadzone hasła nie są takie same. ");

                return "redirect:/change_passwd";
            }

        } else {
//            wrong current password
            err = new String("Podano złe hasło.");

            return "redirect:/change_passwd";
        }

    }

    @GetMapping("/account/destroy")
    public String logout(HttpServletRequest request) {
        if ( request.getSession().getAttribute("user") == null ){
            return "redirect:/login";
        }

        request.getSession().invalidate();

        return "redirect:/login";
    }
}
