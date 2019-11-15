package com.tigra.ats.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigra.ats.domain.DBFile;
import com.tigra.ats.domain.Employee;
import com.tigra.ats.repository.DBFileRepository;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.paginate.*;
import com.tigra.ats.service.paginate.factory.EmployeePaginatorFactory;
import com.tigra.ats.service.searchengine.parameter.SearchParameter;
import com.tigra.ats.service.searchengine.employee.EmployeeSearchType;
import com.tigra.ats.service.searchengine.employee.EmployeeSearchTypeEnum;
import com.tigra.ats.service.searchengine.SearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DBFileRepository dbFileRepository;
    private EmployeePaginatorFactory employeePaginatorFactory;

    public EmployeeService(EmployeeRepository employeeRepository, DBFileRepository dbFileRepository, EmployeePaginatorFactory employeePaginatorFactory) {
        this.employeeRepository = employeeRepository;
        this.dbFileRepository = dbFileRepository;
        this.employeePaginatorFactory = employeePaginatorFactory;
    }

    public void createEmployee(Employee employee, String CVFile) {
        DBFile CV = convertResponse(CVFile);
        employee.setCV(CV);
        dbFileRepository.save(CV);
        employeeRepository.save(employee);
    }

    private DBFile convertResponse(String resp) {
        ObjectMapper objectMapper = new ObjectMapper();
        DBFile CV = null;
        try {
            CV = objectMapper.readValue(resp, DBFile.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CV;
    }

    public Page<Employee> getAvailableEmployees(int pageNumber, String name) {
        Pageable pageRequest = PageRequest.of(pageNumber, 2);
        Employee employee = new Employee();
        employee.setFirstName(name);
        SearchParameter parameter
                = employeePaginatorFactory.createSearchParameter(new EmployeeSearchType(EmployeeSearchTypeEnum.BY_FIRST_NAME), new SearchFilter<>(employee));
        SearchEngine engine = employeePaginatorFactory.createSearchEngine(parameter, pageRequest);
        EmployeePaginator paginator = new EmployeePaginator(engine);
        return paginator.getPage();
    }
}
