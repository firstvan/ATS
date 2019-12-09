package com.tigra.ats.controller;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.service.JobService;
import com.tigra.ats.service.paginate.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/job-operations/{actualPage}")
    public String getJobOperationPage(Model model, Job job, @PathVariable("actualPage") int actualPage) {
        Paginator paginator = jobService.getPaginator(actualPage, job);

        if(!paginator.isValidPage())
            return "error";

        model.addAttribute("jobs", paginator.getActualPage());
        model.addAttribute("actualPage", actualPage);
        model.addAttribute("numberOfPages", paginator.getNumberOfPages());
        model.addAttribute("hasNext", paginator.hasNextPage());
        model.addAttribute("hasPrev", paginator.hasPrevPage());
        model.addAttribute("types", jobService.getTypes());
        model.addAttribute("levels", jobService.getLevels());
        model.addAttribute("locations", jobService.getLocations());
        return "joboperations";
    }

    @PostMapping("/save-job-props")
    public String createJobProps(@RequestParam("job-name") String name,
                            @RequestParam("job-level") String level,
                            @RequestParam("location") String location,
                                 RedirectAttributes redirectAttributes) {
        String message = jobService.saveAvailableJobProperties(name, level, location);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/job-operations/1";
    }

    @PostMapping("/create-job")
    public String createJob(@ModelAttribute Job job, RedirectAttributes redirectAttributes) {
        boolean success = jobService.createJob(job);
        if(success) {
            redirectAttributes.addFlashAttribute("message", "Sikeresen meghírdetted az állást!");
        }
        else {
            redirectAttributes.addFlashAttribute("message", "Már létezik ilyen meghírdetett állás!");
        }
        return "redirect:/job-operations/1";
    }

    @PostMapping("/delete-job")
    public String deleteJob(@RequestParam("job_id") Long id) {
        jobService.deleteJob(id);
        return "redirect:/job-operations/1";
    }
}
