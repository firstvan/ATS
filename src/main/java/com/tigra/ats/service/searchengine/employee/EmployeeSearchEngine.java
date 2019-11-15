package com.tigra.ats.service.searchengine.employee;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.exception.NotFoundSearchParameterException;
import com.tigra.ats.service.searchengine.parameter.EmployeeSearchParameter;
import com.tigra.ats.service.searchengine.parameter.SearchParameter;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component("employeeSearchEngine")
public class EmployeeSearchEngine implements PaginatedSearchEngine {
    private EmployeeRepository employeeRepository;
    private Pageable pageable;
    private SearchParameter searchParameter;

    @Autowired
    public EmployeeSearchEngine(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void setSearchParameter(SearchParameter searchParameter) {
        this.searchParameter  = searchParameter;
    }

    @Override
    public void setActualPage(Pageable page) {
        this.pageable = page;
    }

    @Override
    public Page<Employee> search() throws NotFoundSearchParameterException {
        if(pageable == null || searchParameter == null)
            throw new NotFoundSearchParameterException();

        EmployeeSearchTypeEnum actualTypeEnum = ((EmployeeSearchType)searchParameter.getSearchType()).getType();
        Employee filterEmployee = ((EmployeeSearchParameter) searchParameter).getFilter().getParameter();

        Page<Employee> actualPage = null;
        switch (actualTypeEnum) {
            case ALL:
                actualPage = employeeRepository.findAll(pageable);
                break;
            case BY_FIRST_NAME:
                actualPage = employeeRepository.findByFirstNameIsContaining(pageable, filterEmployee.getFirstName());
                break;
            case BY_LAST_NAME:
                actualPage = employeeRepository.findByLastNameIsContaining(pageable, filterEmployee.getLastName());
                break;
        }
        return actualPage;
    }
}
