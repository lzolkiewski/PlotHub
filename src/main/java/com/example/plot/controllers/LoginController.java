package com.example.plot.controllers;

import com.example.plot.database.User;
import com.example.plot.management.LoginRegister;
import com.example.plot.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    private String warning;

    @RequestMapping("/login")
    public String loginMapping(Model model, HttpServletRequest request, LoginRegister user){
//        if no session then login else go to account
        if ( request.getSession().getAttribute("user") == null ){
            model.addAttribute("user", user);
            model.addAttribute("warning", warning);

            return "login";
        } else {
            return "redirect:/account";
        }
    }

    @PostMapping("/login/createSession")
    public String login(HttpServletRequest request, LoginRegister loginUser) {

        if ( userService.userExists(loginUser) && loginUser.getRePassword().compareTo("") == 0 ) {
//login
            User user;
            
            if ( ( user = userService.getUser(loginUser) ) != null ){
//login successful
                request.getSession().setAttribute("user", user);

                return "redirect:/account";
            } else {
//incorrect password
                warning = new String("Niewłaściwy e-mail lub hasło.");

                return "redirect:/login";
            }
        } else if ( userService.userExists(loginUser) && loginUser.getRePassword().compareTo("") != 0 ) {
//user tried to register but the same user already exists
            warning = new String("Użytkownik już istnieje.");

            return "redirect:/login";
        } else if ( !userService.userExists(loginUser) && loginUser.getRePassword().compareTo("") != 0 ) {
            if (loginUser.getPassword().compareTo(loginUser.getRePassword())==0) {
//register if passwords match
                User user = new User();

                user.setEmail(loginUser.getEmail());
                user.setPassword(loginUser.getPassword());
//both adding user to session and do database
                request.getSession().setAttribute("user", userService.addUser(user));

                return "redirect:/account";
            } else {
//not matching passwords
                warning = new String("Hasła muszą być takie same");

                return "redirect:/login";
            }
        } else {
//user tried to login but incorrect credentials
            warning = new String("Niewłaściwy e-mail lub hasło.");

            return "redirect:/login";
        }
    }

}
