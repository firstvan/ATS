package com.tigra.ats.service;

import com.tigra.ats.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private JobRepository jobRepository;
    private JobRegister jobRegister;

    @Autowired
    public JobService(JobRepository jobRepository, JobRegister jobRegister) {
        this.jobRepository = jobRepository;
        this.jobRegister = jobRegister;
    }

    public void saveAvailableJobProperties(String type, String level, String city) {
        jobRegister.saveType(type);
        jobRegister.saveLevel(level);
        jobRegister.saveLocation(city);
    }

    public void createJob(String type, String level, String city) {
        jobRegister.createJob(type, level, city);
    }

}
