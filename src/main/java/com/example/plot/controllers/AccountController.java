package com.example.plot.controllers;

import com.example.plot.jpa.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
    @RequestMapping("/account")
    public String accountMapping(Model model, HttpServletRequest request) {
        User user = ( User ) request.getSession().getAttribute("user");

//        model.addAttribute("user", user); // just to check if all good

        return "account";
    }

    @RequestMapping("/new_offer")
    public String newOfferMapping(Model model){
        // TODO: 12.12.2020 manage new offer
        return "new_offer";
    }

    @RequestMapping("/change_email")
    public String changeEmailMapping(Model model){
        // TODO: 12.12.2020 add change email
        return "change_email";
    }

    @RequestMapping("/change_passwd")
    public String changePasswordMapping(Model model){
        // TODO: 12.12.2020 add change password
        

        return "change_passwd";
    }

    @GetMapping("/account/destroy")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
