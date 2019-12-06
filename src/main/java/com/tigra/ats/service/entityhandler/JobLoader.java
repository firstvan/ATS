package com.tigra.ats.service.entityhandler;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JobLoader {

    private JobPropertyHandler jobPropertyHandler;
    private JobRepository jobRepository;

    @Autowired
    public JobLoader(JobPropertyHandler jobPropertyHandler, JobRepository repository) {
        this.jobPropertyHandler = jobPropertyHandler;
        this.jobRepository = repository;
    }

    public Optional<Job> findByProps(JobType type, JobLevel level, Location location) {
        return jobRepository.findByTypeAndLevelAndLocation(type, level, location);
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
