package com.tigra.ats.controller;


import com.tigra.ats.domain.*;
import com.tigra.ats.repository.*;
import com.tigra.ats.service.paginate.Paginator;
import com.tigra.ats.service.searchengine.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JelentkezesekController {


    @Autowired
    JobTypeRepository jobTypeRepository;

    @Autowired
    JobLevelRepository jobLevelRepository;

    @Autowired
    LocationRepository locationRepository;


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    @Qualifier("EmployeeSearchEngine")
    SearchEngine searchEngine;

    @GetMapping({"/jelentkezesek"})
    public String jelentkezesek(Model model){

        List<Job> jobs = jobRepository.findAll();
        List<Employee> applicants = new ArrayList<Employee>();

        for(com.tigra.ats.domain.Job Job: jobs){
           applicants.addAll(Job.getRegisteredEmployees());
        }
            model.addAttribute("applicants", applicants);
        return "jelentkezesek";
    }


    @RequestMapping(value = "/jelentkezesek/get-applicant/{id}/{tid}/{lid}/{loid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Employee getApplicant(@PathVariable("id") long id, @PathVariable("tid") long tid, @PathVariable("lid") long lid, @PathVariable("loid") long loid){

        Employee e = employeeRepository.findById(id).get();

        JobType type = jobTypeRepository.findById(tid);
        type.setJobs(null);

        JobLevel level = jobLevelRepository.findById(lid);
        level.setJobs(null);
        
        Location loc = locationRepository.findById(loid);
        loc.setJobs(null);

        e.setPreferredJob(new Job(type, level, loc));

        System.out.println(e.getPreferredJob().getType().getName());

        return e;

    }

}
