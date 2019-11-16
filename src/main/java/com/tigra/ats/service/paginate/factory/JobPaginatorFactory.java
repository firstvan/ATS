package com.tigra.ats.service.paginate.factory;

import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.job.JobSearchParameter;
import com.tigra.ats.service.searchengine.SearchParameter;
import com.tigra.ats.service.searchengine.job.JobSearchEngine;
import com.tigra.ats.service.searchengine.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component("jobPaginatorFactory")
public class JobPaginatorFactory implements PaginatorFactory {
    private JobSearchEngine searchEngine;

    @Autowired
    public JobPaginatorFactory(JobSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public SearchParameter createSearchParameter(SearchType type, SearchFilter filter) {
        return new JobSearchParameter(type, filter);
    }

    @Override
    public PaginatedSearchEngine createSearchEngine(SearchParameter parameter, Pageable pageable) {
        searchEngine.setActualPage(pageable);
        searchEngine.setSearchParameter(parameter);
        return searchEngine;
    }
}
