package com.tigra.ats.service;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.service.entityhandler.JobRegister;
import com.tigra.ats.service.entityhandler.JobLoader;
import com.tigra.ats.service.searchengine.job.JobSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private JobLoader jobLoader;
    private JobRegister jobRegister;
    private JobSearchEngine searchEngine;

    @Autowired
    public JobService(JobLoader jobLoader, JobRegister jobRegister, JobSearchEngine searchEngine) {
        this.jobLoader = jobLoader;
        this.jobRegister = jobRegister;
        this.searchEngine = searchEngine;
    }

    public void saveAvailableJobProperties(String type, String level, String city) {
        jobRegister.saveType(type);
        jobRegister.saveLevel(level);
        jobRegister.saveLocation(city);
    }

    public void createJob(String type, String level, String city) {
        jobRegister.createJob(type, level, city, true);
    }

    public void deleteJob(Long id) {
        jobRegister.deleteJob(id);
    }

    public Page<Job> getAvailableJobs(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 2);
        searchEngine.setActualPage(pageable);
        return searchEngine.search();
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
