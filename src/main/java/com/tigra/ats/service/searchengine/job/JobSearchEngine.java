package com.tigra.ats.service.searchengine.job;

import com.tigra.ats.domain.Job;
import com.tigra.ats.repository.JobRepository;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class JobSearchEngine implements PaginatedSearchEngine {
    private JobRepository jobRepository;
    private Pageable pageable;
    private SearchFilter<Job> filter;

    @Autowired
    public JobSearchEngine(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void setActualPage(Pageable pageable) {
        this.pageable = pageable;
    }

    @Override
    public void setSearchFilter(SearchFilter filter) {
        this.filter = filter;
    }

    @Override
    public Page<Job> search() {
        return jobRepository.findAll(pageable);
    }
}
