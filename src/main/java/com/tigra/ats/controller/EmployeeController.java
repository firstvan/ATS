package com.tigra.ats.controller;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.service.EmployeeService;
import com.tigra.ats.service.JeloltService;
import com.tigra.ats.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class EmployeeController {
    private JobService jobService;
    private EmployeeService employeeService;
    private JeloltService jeloltService;

    @Autowired
    public EmployeeController(JobService jobService, EmployeeService employeeService,JeloltService jeloltService) {
        this.jobService = jobService;
        this.employeeService = employeeService;
        this.jeloltService=jeloltService;
    }

    @GetMapping("/employee-creator/{actualPage}")
    public String displayEmployeeCreator(Model model, @RequestParam(defaultValue = "") String name, @PathVariable("actualPage") int actualPage) {
        int numberOfPages = jeloltService.getNumberOfPages();
        if(actualPage > 1 && actualPage > (numberOfPages + 1))
            return "error";
        else {
            model.addAttribute("jelolts", jeloltService.getAvailableJelolts(actualPage - 1, name));
            model.addAttribute("actualPage", actualPage);
            model.addAttribute("numberOfPages", numberOfPages);
        }
        model.addAttribute("employee", new Employee());
        model.addAttribute("types", jobService.getTypes());
        model.addAttribute("levels", jobService.getLevels());
        model.addAttribute("locations", jobService.getLocations());
        return "employee";
    }

    @PostMapping("/create-employee")
    @ResponseStatus(value = HttpStatus.OK)
    public void createEmployee(@ModelAttribute Employee employee, @RequestParam("CVFile") String CVFile) {
        employeeService.createEmployee(employee, CVFile);
    }
}
