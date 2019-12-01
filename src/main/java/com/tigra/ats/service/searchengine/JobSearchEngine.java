package com.tigra.ats.service.searchengine;

import com.tigra.ats.domain.Job;
import com.tigra.ats.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("JobSearchEngine")
public class JobSearchEngine implements PaginatedSearchEngine {
    private JobRepository jobRepository;
    private Pageable pageable;
    private SearchFilter<Job> filter;

    @Autowired
    public JobSearchEngine(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Page<Job> search(Pageable pageable, SearchFilter filter) {
        return jobRepository.findAll(pageable);
    }

    @Override
    public List<Job> search() {
        return jobRepository.findAll();
    }
}
