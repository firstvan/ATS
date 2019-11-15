package com.tigra.ats.service.searchengine.parameter;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.SearchType;

public class EmployeeSearchParameter implements SearchParameter {
    private SearchType searchType;
    private SearchFilter<Employee> employeeFilter;

    public EmployeeSearchParameter(SearchType searchType, SearchFilter<Employee> employeeFilter) {
        this.searchType = searchType;
        this.employeeFilter = employeeFilter;
    }

    @Override
    public SearchType getSearchType() {
        return searchType;
    }

    @Override
    public SearchFilter<Employee> getFilter() {
        return employeeFilter;
    }
}
