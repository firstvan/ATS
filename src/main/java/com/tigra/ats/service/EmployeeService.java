package com.tigra.ats.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigra.ats.domain.DBFile;
import com.tigra.ats.domain.Employee;
import com.tigra.ats.domain.Job;
import com.tigra.ats.repository.DBFileRepository;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.entityhandler.JobRegister;
import com.tigra.ats.service.paginate.*;
import com.tigra.ats.service.paginate.factory.EmployeePaginatorFactory;
import com.tigra.ats.service.searchengine.SearchParameter;
import com.tigra.ats.service.searchengine.employee.EmployeeSearchType;
import com.tigra.ats.service.searchengine.employee.EmployeeSearchTypeEnum;
import com.tigra.ats.service.searchengine.SearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DBFileRepository dbFileRepository;
    private EmployeePaginatorFactory employeePaginatorFactory;
    private JobRegister jobRegister;

    public EmployeeService(EmployeeRepository employeeRepository, DBFileRepository dbFileRepository, EmployeePaginatorFactory employeePaginatorFactory, JobRegister jobRegister) {
        this.employeeRepository = employeeRepository;
        this.dbFileRepository = dbFileRepository;
        this.employeePaginatorFactory = employeePaginatorFactory;
        this.jobRegister = jobRegister;
    }

    public boolean createJobRegistration(Job job, String employees) {
        List<Long> IDs = getEmployeeIDListFromString(employees);
        List<Employee> employeeList = getEmployeesFromIDs(IDs);
        Job createdJob = jobRegister.createJob(job.getType().getName(), job.getLevel().getLevel(), job.getLocation().getCity(), false);
        for(Employee employee : employeeList) {
            employee.addJob(createdJob);
        }

        try {
            employeeRepository.saveAll(employeeList);
        } catch (InvalidDataAccessApiUsageException ex) {
            return false;
        }

        return true;
    }

    private List<Long> getEmployeeIDListFromString(String employeeIDs) {
        String[] IDs = employeeIDs.split(",");
        List<Long> IDList = new ArrayList<>();
        for(String id : IDs) {
            IDList.add(Long.parseLong(id));
        }
        return IDList;
    }

    private List<Employee> getEmployeesFromIDs(List<Long> IDs) {
        return (ArrayList<Employee>)employeeRepository.findAllById(IDs);
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

    public void delete(long id) {
        employeeRepository.deleteById(id);
    }
    public void update(Employee employee){
        employeeRepository.save(employee);
    }
    public Optional<Employee> getOne(long id){
        return employeeRepository.findById(id);
    }
}
