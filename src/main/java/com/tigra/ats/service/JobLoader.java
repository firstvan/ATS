package com.tigra.ats.service;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobLoader {
    private JobRepository jobRepository;
    private JobPropertyHandler jobPropertyHandler;

    @Autowired
    public JobLoader(JobRepository jobRepository, JobPropertyHandler jobPropertyHandler) {
        this.jobRepository = jobRepository;
        this.jobPropertyHandler = jobPropertyHandler;
    }

    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }

    public List<JobType> getTypes() {
        return jobPropertyHandler.findAllType();
    }

    public List<JobLevel> getLevels() {
        return jobPropertyHandler.findAllLevel();
    }

    public List<Location> getLocations() {
        return jobPropertyHandler.findAllLocation();
    }
}
