package com.tigra.ats.service.searchengine.job;

import com.tigra.ats.domain.Job;
import com.tigra.ats.repository.JobRepository;
import com.tigra.ats.service.exception.NotFoundSearchParameterException;
import com.tigra.ats.service.searchengine.SearchParameter;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class JobSearchEngine implements PaginatedSearchEngine {
    private JobRepository jobRepository;
    private Pageable pageable;
    private SearchParameter searchParameter;

    @Autowired
    public JobSearchEngine(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void setActualPage(Pageable pageable) {
        this.pageable = pageable;
    }

    @Override
    public void setSearchParameter(SearchParameter parameter) {
        this.searchParameter = parameter;
    }

    @Override
    public Iterable search() throws NotFoundSearchParameterException {
        if(pageable == null || searchParameter == null)
            throw new NotFoundSearchParameterException();

        JobSearchTypeEnum typeEnum = ((JobSearchType) searchParameter.getSearchType()).getType();
        Job jobFilter = ((Job) searchParameter.getFilter().getParameter());

        Page<Job> actualPage = null;
        switch (typeEnum) {
            case ALL:
                actualPage = jobRepository.findAll(pageable);
                break;
            case BY_STATUS:
                actualPage = jobRepository.findAllByDisplayStatus(pageable, jobFilter.isDisplayStatus());
        }
        return actualPage;
    }
}
