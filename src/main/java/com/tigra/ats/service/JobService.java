package com.tigra.ats.service;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.service.entityhandler.JobRegister;
import com.tigra.ats.service.entityhandler.JobLoader;
import com.tigra.ats.service.paginate.Paginator;
import com.tigra.ats.service.searchengine.JobSearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private JobLoader jobLoader;
    private JobRegister jobRegister;
    private JobSearchEngine searchEngine;
    private Paginator paginator;

    @Autowired
    public JobService(JobLoader jobLoader, JobRegister jobRegister, @Qualifier("JobPaginator") Paginator paginator) {
        this.jobLoader = jobLoader;
        this.jobRegister = jobRegister;
        this.paginator = paginator;
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

    public Paginator getPaginator(int actualPageNumber, Job job) {
       paginator.setNumberOfItemsOnOnePage(2);
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
