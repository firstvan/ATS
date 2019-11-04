package com.tigra.ats.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigra.ats.domain.DBFile;
import com.tigra.ats.domain.Employee;
import com.tigra.ats.service.EmployeeService;
import com.tigra.ats.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class EmployeeController {
    private JobService jobService;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(JobService jobService, EmployeeService employeeService) {
        this.jobService = jobService;
        this.employeeService = employeeService;
    }

    @GetMapping("/employee-creator")
    public String displayEmployeeCreator(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("types", jobService.getTypes());
        model.addAttribute("levels", jobService.getLevels());
        model.addAttribute("locations", jobService.getLocations());
        return "employee";
    }

    @PostMapping("/create-employee")
    public String createEmployee(@ModelAttribute Employee employee, @RequestParam("CVFile") String CVFile) {
        employeeService.createEmployee(employee, CVFile);
        return "redirect:/employee-creator";
    }
}
