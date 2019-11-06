package com.tigra.ats.service.logic;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class JeloltLoader {
    private final int JELOLTS_IN_ONE_PAGE = 10;

   private EmployeeRepository employeeRepository;

    @Autowired
    public JeloltLoader(EmployeeRepository employeeRepository) {
       this.employeeRepository=employeeRepository;
    }

    public Page<Employee> getEmployeePage(int pageNumber, String name) {
        Pageable pageRequest = PageRequest.of(pageNumber, JELOLTS_IN_ONE_PAGE);
        Page<Employee> page = employeeRepository.findByFirstNameIsContaining(pageRequest,name);
        return page;
    }

    public int getNumberOfPages() {
        return (int) employeeRepository.count() / (JELOLTS_IN_ONE_PAGE + 1);
    }

}
