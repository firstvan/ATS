package com.tigra.ats.service.logic;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobLoader {
    private final int JOBS_IN_ONE_PAGE = 10;

    private JobRepository jobRepository;
    private JobPropertyHandler jobPropertyHandler;

    @Autowired
    public JobLoader(JobRepository jobRepository, JobPropertyHandler jobPropertyHandler) {
        this.jobRepository = jobRepository;
        this.jobPropertyHandler = jobPropertyHandler;
    }

    public Page<Job> getJobPage(int pageNumber) {
        Pageable pageRequest = PageRequest.of(pageNumber, JOBS_IN_ONE_PAGE);
        Page<Job> page = jobRepository.findAll(pageRequest);
        return page;
    }

    public int getNumberOfPages() {
        return (int) jobRepository.count() / (JOBS_IN_ONE_PAGE + 1);
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
