package com.tigra.ats.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping({"/login", "/"})
    public String index() {
        return "login";
    }

    @GetMapping("/home")
    public String menu() {
        return "home";
    }

}
