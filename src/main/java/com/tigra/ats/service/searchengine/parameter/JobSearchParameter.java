package com.tigra.ats.service.searchengine.parameter;

import com.tigra.ats.domain.Job;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.SearchType;

public class JobSearchParameter implements SearchParameter {
    private SearchType type;
    private SearchFilter<Job> jobFilter;


    public JobSearchParameter(SearchType type, SearchFilter<Job> jobFilter) {
        this.type = type;
        this.jobFilter = jobFilter;
    }

    @Override
    public SearchType getSearchType() {
        return type;
    }

    @Override
    public SearchFilter<Job> getFilter() {
        return jobFilter;
    }
}
