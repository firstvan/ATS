package com.tigra.ats.controller;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.domain.Job;
import com.tigra.ats.service.EmployeeService;
import com.tigra.ats.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public String displayEmployeeCreator(Model model, @RequestParam(defaultValue = "") String name, @PathVariable("actualPage") int actualPage) {
        Page<Employee> employeePage = employeeService.getAvailableEmployees(actualPage - 1, name);
        int numberOfPages = employeePage.getTotalPages() - 1;

        if(actualPage > 1 && actualPage > (numberOfPages + 1))
            return "error";
        else {
            model.addAttribute("jelolts", employeePage);
            model.addAttribute("actualPage", actualPage);
            model.addAttribute("numberOfPages", numberOfPages);
        }
        model.addAttribute("employee", new Employee());
        model.addAttribute("types", jobService.getTypes());
        model.addAttribute("levels", jobService.getLevels());
        model.addAttribute("locations", jobService.getLocations());
        return "employee";
    }

    @PostMapping(value = "/create-employee", produces = "application/json; charset=UTF-8")
    @ResponseStatus(value = HttpStatus.OK)
    public void createEmployee(@ModelAttribute Employee employee, @RequestParam("CVFile") String CVFile) {
        employeeService.createEmployee(employee, CVFile);
    }

    @PostMapping("/create-job-registration")
    @ResponseBody
    public ResponseTransfer createJobRegistration(@ModelAttribute Job job, @RequestParam("employee_ids") String employees) {
        boolean success = employeeService.createJobRegistration(job, employees);
        System.out.println(success);
        if(success) {
            return new ResponseTransfer("Sikeres létrehozás");
        }
        else
            return new ResponseTransfer("Már létezik ilyen regisztráció!");
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        employeeService.delete(id);
        return "redirect:/employee-creator/1";
    }
    @RequestMapping(value="/update",method={RequestMethod.PUT,RequestMethod.GET})
    public String update(Employee employee){
        employeeService.update(employee);
        return "redirect:/employee-creator/1";
    }
    @RequestMapping("/getOne")
    @ResponseBody
    public Optional<Employee> getOne(long id){
        return employeeService.getOne(id);
    }
}
