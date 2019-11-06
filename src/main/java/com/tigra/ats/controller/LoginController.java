package com.tigra.ats.controller;

import com.tigra.ats.service.logic.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {


    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


    @GetMapping({"/login", "/"})
    public String login(Model model) {
        model.addAttribute("errorMessage", customAuthenticationFailureHandler.errorMessage);
        customAuthenticationFailureHandler.errorMessage = null;


        return "login";
    }

    @GetMapping("/home")
    public String menu() {
        return "home";
    }

}
