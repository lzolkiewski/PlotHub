package com.example.plot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    @RequestMapping("/newoffer")
    public String newofferMapping(Model model){
        return "newoffer";
    }

    @RequestMapping("/change_email")
    public String changeEmailMapping(Model model){
        return "change_email";
    }

    @RequestMapping("/change_passwd")
    public String changePasswordMapping(Model model){
        return "change_passwd";
    }
}
