package com.tigra.ats.controller;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class EmployeeController {
    private JobService jobService;

    @Autowired
    public EmployeeController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/employee-creator")
    public String displayEmployeeCreator(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("types", jobService.getTypes());
        model.addAttribute("levels", jobService.getLevels());
        model.addAttribute("locations", jobService.getLocations());
        return "employee";
    }

    @GetMapping("/create-employee")
    public String createEmployee(@ModelAttribute Employee employee) {
        System.out.println(employee);
        return "redirect:/employee-creator";
    }
}
