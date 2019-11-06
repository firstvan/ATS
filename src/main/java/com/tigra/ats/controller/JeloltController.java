package com.tigra.ats.controller;

import com.tigra.ats.service.JeloltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JeloltController {
    private JeloltService jeloltService;
    @Autowired
    JeloltController(JeloltService jeloltService){this.jeloltService=jeloltService;}
    @GetMapping("/jeloltek/{actualPage}")

    public String listOfJeloltek(Model model, @RequestParam(defaultValue = "") String name, @PathVariable("actualPage") int actualPage){
        int numberOfPages = jeloltService.getNumberOfPages();
        if(actualPage > 1 && actualPage > (numberOfPages + 1))
            return "error";
        else {
            model.addAttribute("jelolts", jeloltService.getAvailableJelolts(actualPage - 1,name));
            model.addAttribute("actualPage", actualPage);
            model.addAttribute("numberOfPages", numberOfPages);

            /*List<Jelolt> Jeloltek = jeloltService.findByFullNameIsContaining(name);

            model.addAttribute("jeloltek", Jeloltek);*/
            return "jeloltek";
        }
    }

}
