package com.tigra.ats.service.searchengine;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("EmployeeSearchEngine")
public class EmployeeSearchEngine implements PaginatedSearchEngine {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeSearchEngine(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> search(Pageable pageable, SearchFilter searchFilter) {
        Employee employee = (Employee) searchFilter.getParameter();
        Page<Employee> employees
                = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContaining
                                        (pageable, employee.getFirstName(), employee.getLastName(), employee.getMail());

        List<Employee> filteredEmployees = employees
                        .stream()
                        .filter(e -> {
                            JobType typeFilter = employee.getPreferredJob().getType();
                            JobLevel levelFilter = employee.getPreferredJob().getLevel();
                            Location locationFilter = employee.getPreferredJob().getLocation();

                            if(typeFilter == null && levelFilter == null && locationFilter == null) {
                                return true;
                            }
                            else if(levelFilter == null && locationFilter == null) {
                                boolean isEqual = e.getPreferredJob().getType().equals(typeFilter);
                                return isEqual;
                            }
                            else if(typeFilter == null && locationFilter == null) {
                                return e.getPreferredJob().getLevel().equals(levelFilter);
                            }
                            else if(typeFilter == null && levelFilter == null) {
                                return e.getPreferredJob().getLocation().equals(locationFilter);
                            }
                            else if(locationFilter == null) {
                                return e.getPreferredJob().getType().equals(typeFilter) && e.getPreferredJob().getLevel().equals(levelFilter);
                            }
                            else if(levelFilter == null) {
                                return e.getPreferredJob().getType().equals(typeFilter) && e.getPreferredJob().getLocation().equals(locationFilter);
                            }
                            else if(typeFilter == null) {
                                return e.getPreferredJob().getLevel().equals(levelFilter) && e.getPreferredJob().getLocation().equals(locationFilter);
                            }
                            else {
                                return e.getPreferredJob().getLocation().equals(locationFilter)
                                        && e.getPreferredJob().getLevel().equals(levelFilter)
                                        && e.getPreferredJob().getType().equals(typeFilter);
                            }
                        })
                .collect(Collectors.toList());
        return new PageImpl<>(filteredEmployees);
    }

    @Override
    public List<Employee> search() {
        return employeeRepository.findAll();
    }
}
