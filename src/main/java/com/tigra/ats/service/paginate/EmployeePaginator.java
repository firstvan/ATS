package com.tigra.ats.service.paginate;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component("employeePaginator")
public class EmployeePaginator implements Paginator {
    private EmployeeRepository employeeRepository;
    private Filter filter;
    private int jobsInOnePage;

    @Autowired
    public EmployeePaginator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.jobsInOnePage = 10;
    }

    @Override
    public void setMaxItemInOnePage(int numberOfItems) {
        this.jobsInOnePage = numberOfItems;
    }

    @Override
    public Page<Employee> getPage(int index) {
        Page<Employee> actualPage = null;
        if(filter == null || filter.isEmpty()) {
            Pageable pageRequest = PageRequest.of(index, jobsInOnePage);
            actualPage = employeeRepository.findAll(pageRequest);
        }
        else if(filter.getFilter() instanceof String) {
            Pageable pageRequest = PageRequest.of(index, jobsInOnePage);
            actualPage = employeeRepository.findByFirstNameIsContaining(pageRequest, (String) filter.getFilter());
        }
        return actualPage;
    }

    @Override
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}