package com.tigra.ats.controller;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/job-operations/{actualPage}")
    public String getJobOperationPage(Model model, @PathVariable("actualPage") int actualPage) {
        Page<Job> jobPage = jobService.getAvailableJobs(actualPage - 1);
        int numberOfPages = 1;
        if(jobPage.getTotalPages() > 0) {
            numberOfPages = jobPage.getTotalPages();
        }
        List<JobType> types = jobService.getTypes();

        if(actualPage > 1 && actualPage > numberOfPages)
            return "error";
        else {
            model.addAttribute("jobs", jobPage);
            model.addAttribute("actualPage", actualPage);
            model.addAttribute("numberOfPages", numberOfPages);
            model.addAttribute("types", jobService.getTypes());
            model.addAttribute("levels", jobService.getLevels());
            model.addAttribute("locations", jobService.getLocations());
            return "joboperations";
        }
    }

    @PostMapping("/save-job-props")
    public String createJobProps(@RequestParam("job-name") String name,
                            @RequestParam("job-level") String level,
                            @RequestParam("location") String location) {
        jobService.saveAvailableJobProperties(name, level, location);
        return "redirect:/job-operations/1";
    }

    @PostMapping("/create-job")
    public String createJob(@RequestParam("job-name") String name,
                            @RequestParam("job-level") String level,
                            @RequestParam("location") String location) {
        jobService.createJob(name, level, location);
        return "redirect:/job-operations/1";
    }

    @PostMapping("/delete-job/{id}")
    public String deleteJob(@PathVariable("id") Long id) {
        jobService.deleteJob(id);
        return "redirect:/job-operations/1";
    }
}
