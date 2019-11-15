package com.tigra.ats.service;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.service.entityhandler.JobRegister;
import com.tigra.ats.service.entityhandler.JobLoader;
import com.tigra.ats.service.paginate.JobPaginator;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.paginate.factory.JobPaginatorFactory;
import com.tigra.ats.service.searchengine.parameter.SearchParameter;
import com.tigra.ats.service.searchengine.job.JobSearchType;
import com.tigra.ats.service.searchengine.job.JobSearchTypeEnum;
import com.tigra.ats.service.searchengine.SearchEngine;
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
    private JobPaginatorFactory paginatorFactory;

    @Autowired
    public JobService(JobLoader jobLoader, JobRegister jobRegister, JobPaginatorFactory paginatorFactory) {
        this.jobLoader = jobLoader;
        this.jobRegister = jobRegister;
        this.paginatorFactory = paginatorFactory;
    }

    public void saveAvailableJobProperties(String type, String level, String city) {
        jobRegister.saveType(type);
        jobRegister.saveLevel(level);
        jobRegister.saveLocation(city);
    }

    public void createJob(String type, String level, String city) {
        jobRegister.createJob(type, level, city);
    }

    public void deleteJob(Long id) {
        jobRegister.deleteJob(id);
    }

    public Page<Job> getAvailableJobs(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 2);
        Job job = new Job();
        SearchParameter searchParameter = paginatorFactory.createSearchParameter(new JobSearchType(JobSearchTypeEnum.ALL), new SearchFilter<Job>(job));
        SearchEngine searchEngine = paginatorFactory.createSearchEngine(searchParameter, pageable);
        JobPaginator paginator = new JobPaginator(searchEngine);
        return paginator.getPage();
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
