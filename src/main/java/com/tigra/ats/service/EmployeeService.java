package com.tigra.ats.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigra.ats.domain.DBFile;
import com.tigra.ats.domain.Employee;
import com.tigra.ats.repository.DBFileRepository;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.paginate.Filter;
import com.tigra.ats.service.paginate.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DBFileRepository dbFileRepository;
    private Paginator paginator;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DBFileRepository dbFileRepository, @Qualifier("employeePaginator") Paginator paginator) {
        this.employeeRepository = employeeRepository;
        this.dbFileRepository = dbFileRepository;
        this.paginator = paginator;
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
        paginator.setFilter(new Filter<>(name));
        paginator.setMaxItemInOnePage(2);
        return paginator.getPage(pageNumber);
    }
}
