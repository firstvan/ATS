package com.tigra.ats.service.searchengine;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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
        Page<Employee> employees;

        if(employee.getType() == null && employee.getLevel() == null && employee.getLocation() == null) {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContaining(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail()
            );
        }
        else if(employee.getLevel() == null && employee.getLocation() == null) {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndType(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail(),
                    employee.getType()
            );
        }
        else if(employee.getType() == null && employee.getLocation() == null) {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndLevel(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail(),
                    employee.getLevel()
            );
        }
        else if(employee.getType() == null && employee.getLevel() == null) {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndLocation(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail(),
                    employee.getLocation()
            );
        }
        else if(employee.getLocation() == null) {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndTypeAndLevel(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail(),
                    employee.getType(),
                    employee.getLevel()
            );
        }
        else if(employee.getLevel() == null) {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndTypeAndLocation(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail(),
                    employee.getType(),
                    employee.getLocation()
            );
        }
        else if(employee.getType() == null) {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndLevelAndLocation(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail(),
                    employee.getLevel(),
                    employee.getLocation()
            );
        }
        else {
            employees = employeeRepository.findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndTypeAndLevelAndLocation(
                    pageable,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getMail(),
                    employee.getType(),
                    employee.getLevel(),
                    employee.getLocation()
            );
        }
        return employees;
    }

    @Override
    public List<Employee> search() {
        return employeeRepository.findAll();
    }
}
