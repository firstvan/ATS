package com.tigra.ats.controller;

import com.tigra.ats.service.JobPropertyContainer;
import com.tigra.ats.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class JobController {
    private JobService jobService;
    private JobPropertyContainer properties;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @Autowired
    public void setProperties(JobPropertyContainer properties) {
        this.properties = properties;
    }

    @GetMapping("/job-form")
    public String displayJobForm() {
        return "jobform";
    }

    @PostMapping("/save-job-props")
    public String createJobProps(@RequestParam("job-name") String name,
                            @RequestParam("job-level") String level,
                            @RequestParam("location") String location) {
        jobService.saveAvailableJobProperties(name, level, location);
        return "redirect:/job-form";
    }

    @GetMapping("/job-creator")
    public String displayJobCreator(Model model) {
        model.addAttribute("types", properties.getTypes());
        model.addAttribute("levels", properties.getLevels());
        model.addAttribute("locations", properties.getLocations());
        return "jobcreator";
    }

    @PostMapping("/create-job")
    public String createJob(@RequestParam("job-name") String name,
                            @RequestParam("job-level") String level,
                            @RequestParam("location") String location) {
        jobService.createJob(name, level, location);
        return "redirect:/job-creator";
    }
}
