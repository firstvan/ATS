package com.tigra.ats.controller;

import com.tigra.ats.domain.*;
import com.tigra.ats.service.EmployeeService;
import com.tigra.ats.service.JobService;
import com.tigra.ats.service.paginate.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class EmployeeController {
    private JobService jobService;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(JobService jobService, EmployeeService employeeService) {
        this.jobService = jobService;
        this.employeeService = employeeService;
    }

    @GetMapping("/employee-creator/{actualPage}")
    public String searchBy(Model model, Employee employeeFilter, Job jobFilter, @PathVariable("actualPage") int actualPageNumber) {
        employeeFilter.setPreferredJob(jobFilter);
        Paginator paginator = employeeService.getPaginator(actualPageNumber, employeeFilter);

        if(!paginator.isValidPage())
            return "error";

        model.addAttribute("jelolts", paginator.getActualPage());
        model.addAttribute("actualPage", actualPageNumber);
        model.addAttribute("numberOfPages", paginator.getNumberOfPages());
        model.addAttribute("hasNext", paginator.hasNextPage());
        model.addAttribute("hasPrev", paginator.hasPrevPage());
        model.addAttribute("types", jobService.getTypes());
        model.addAttribute("levels", jobService.getLevels());
        model.addAttribute("locations", jobService.getLocations());
        return "employee";
    }

    @PostMapping("/create-employee")
    @ResponseStatus(value = HttpStatus.OK)
    public void createEmployee(@ModelAttribute Employee employee,
                               @ModelAttribute Job job,
                               @RequestParam("CVFile") String CVFile) {
        employeeService.createEmployee(employee, job, CVFile);
    }

    @PostMapping(value = "/create-job-registration", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseTransfer createJobRegistration(@ModelAttribute Job job, @RequestParam("employee_ids") String employees) {
        if(employees.isEmpty()) {
            return new ResponseTransfer("Ki kell választanod legalább egy jelöltet!");
        }

        boolean success = employeeService.createJobRegistration(job, employees);
        if(success) {
            return new ResponseTransfer("Sikeres rögzítés!");
        }
        else {
            return new ResponseTransfer("Már van ilyen aktív státuszú jelentkezés a rendszerben!");
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        employeeService.delete(id);
        return "redirect:/employee-creator/1";
    }

    @GetMapping("/getOne")
    @ResponseBody
    public Optional<Employee> getOne(@RequestParam("id") Long id){
       return employeeService.getOne(id);
    }
}
