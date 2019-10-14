package com.tigra.ats.service;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class JobRegister {
    private JobPropertyHandler jobPropertyHandler;
    private JobRepository jobRepository;

    @Autowired
    public JobRegister(JobPropertyHandler jobPropertyHandler, JobRepository jobRepository) {
        this.jobPropertyHandler = jobPropertyHandler;
        this.jobRepository = jobRepository;
    }

    //TODO lecserélni egy property factoryra
    public void saveType(String type) {
        if(!type.isEmpty()) {
            Optional<JobType> jobType = jobPropertyHandler.getType(type);
            if(!jobType.isPresent())
                jobPropertyHandler.saveType(new JobType(type));
        }
    }

    public void saveLevel(String level) {
        if(!level.isEmpty()) {
            Optional<JobLevel> jobLevel = jobPropertyHandler.getLevel(level);
            if(!jobLevel.isPresent())
                jobPropertyHandler.saveLevel(new JobLevel(level));
        }
    }

    public void saveLocation(String city) {
        if(!city.isEmpty()) {
            Optional<Location> jobLocation = jobPropertyHandler.getLocation(city);
            if(!jobLocation.isPresent())
                jobPropertyHandler.saveLocation(new Location(city));
        }
    }

    public void createJob(String type, String level, String city) {
        Optional<JobType> jobType = jobPropertyHandler.getType(type);
        Optional<JobLevel> jobLevel = jobPropertyHandler.getLevel(level);
        Optional<Location> location = jobPropertyHandler.getLocation(city);

        if(jobType.isPresent() && jobLevel.isPresent() && location.isPresent()) {
            Job createdJob = new Job(jobType.get(), jobLevel.get(), location.get(), LocalDate.now());
            jobRepository.save(createdJob);
        }
        else
            throw new CannotCreateJob();
    }
}
