package com.tigra.ats.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigra.ats.domain.DBFile;
import com.tigra.ats.domain.Employee;
import com.tigra.ats.domain.Job;
import com.tigra.ats.repository.DBFileRepository;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.entityhandler.JobRegister;
import com.tigra.ats.service.paginate.Paginator;
import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DBFileRepository dbFileRepository;
    private JobRegister jobRegister;
    private Paginator paginator;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DBFileRepository dbFileRepository, JobRegister jobRegister, @Qualifier("EmployeePaginator") Paginator paginator) {
        this.employeeRepository = employeeRepository;
        this.dbFileRepository = dbFileRepository;
        this.jobRegister = jobRegister;
        this.paginator = paginator;
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
        return (ArrayList<Employee>) employeeRepository.findAllById(IDs);
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

    public Paginator getPaginator(int pageNumber, Employee filter) {
        filter.setFirstName(getValidString(filter.getFirstName()));
        filter.setLastName(getValidString(filter.getLastName()));
        filter.setMail(getValidString(filter.getMail()));

        paginator.setNumberOfItemsOnOnePage(2);
        paginator.setPageRequest(pageNumber, new SearchFilter<Employee>(filter));
        return paginator;
    }

    private String getValidString(String str) {
        if(str == null)
            return "";
        return str;
    }
}
