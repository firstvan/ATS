package com.tigra.ats.service.paginate;

import com.tigra.ats.domain.Job;
import com.tigra.ats.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component("jobPaginator")
public class JobPaginator implements Paginator {
    private JobRepository jobRepository;
    private int jobsInOnePage;
    private Filter filter;

    @Autowired
    public JobPaginator(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
        this.jobsInOnePage = 10;
    }

    @Override
    public void setMaxItemInOnePage(int numberOfItems) {
        this.jobsInOnePage = numberOfItems;
    }

    @Override
    public Page<Job> getPage(int index) {
        Pageable pageRequest = PageRequest.of(index, jobsInOnePage);
        Page<Job> actualPage = jobRepository.findAll(pageRequest);
        return actualPage;
    }

    @Override
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
