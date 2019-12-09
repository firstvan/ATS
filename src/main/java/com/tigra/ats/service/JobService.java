package com.tigra.ats.service;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.service.entityhandler.JobRegister;
import com.tigra.ats.service.entityhandler.JobLoader;
import com.tigra.ats.service.paginate.Paginator;
import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private JobLoader jobLoader;
    private JobRegister jobRegister;
    private Paginator paginator;

    @Autowired
    public JobService(JobLoader jobLoader, JobRegister jobRegister, @Qualifier("JobPaginator") Paginator paginator) {
        this.jobLoader = jobLoader;
        this.jobRegister = jobRegister;
        this.paginator = paginator;
    }

    public String saveAvailableJobProperties(String type, String level, String city) {
        String message = "";
        boolean success = jobRegister.createJobProperties(type, level, city);
        if(success) {
            message = "Sikeresen meghírdetted a pozíciót!";
        }
        else {
            message = "Már van ilyen meghírdetett pozíció!";
        }
        return message;
    }

    public boolean createJob(Job job) {
        Optional<Job> createdJob = jobRegister.saveJob(job, true);
        return createdJob.isPresent();
    }

    public void deleteJob(Long id) {
        jobRegister.deleteJob(id);
    }

    public Paginator getPaginator(int actualPageNumber, Job job) {
       paginator.setNumberOfItemsOnOnePage(2);
       job.setDisplayStatus(true);
       paginator.setPageRequest(actualPageNumber, new SearchFilter(job));
       return paginator;
    }

    public List<JobType> getTypes() {
        return jobLoader.getTypes();
    }

    public List<JobLevel> getLevels() {
        return jobLoader.getLevels();
    }

    public List<Location> getLocations() {
        return jobLoader.getLocations();
    }
}
