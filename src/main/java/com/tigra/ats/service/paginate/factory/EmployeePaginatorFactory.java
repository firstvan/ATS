package com.tigra.ats.service.paginate.factory;

import com.tigra.ats.service.searchengine.employee.EmployeeSearchParameter;
import com.tigra.ats.service.searchengine.SearchParameter;
import com.tigra.ats.service.searchengine.employee.EmployeeSearchEngine;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.SearchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EmployeePaginatorFactory implements PaginatorFactory {
    private EmployeeSearchEngine searchEngine;

    @Autowired
    public EmployeePaginatorFactory(EmployeeSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public SearchParameter createSearchParameter(SearchType type, SearchFilter filter) {
        return new EmployeeSearchParameter(type, filter);
    }


    @Override
    public PaginatedSearchEngine createSearchEngine(SearchParameter parameter, Pageable pageable) {
        searchEngine.setSearchParameter(parameter);
        searchEngine.setActualPage(pageable);
        return searchEngine;
    }

}