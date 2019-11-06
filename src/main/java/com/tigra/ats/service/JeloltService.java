package com.tigra.ats.service;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.domain.Jelolt;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.repository.JeloltRepository;
import com.tigra.ats.service.logic.JeloltLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class JeloltService {
    private JeloltLoader jeloltLoader;
    private EmployeeRepository employeeRepository;
    @Autowired
    JeloltService(JeloltLoader jeloltLoader, EmployeeRepository employeeRepository){this.jeloltLoader=jeloltLoader; this.employeeRepository=employeeRepository;}

    public Page<Employee> getAvailableJelolts(int pageNumber, String name) {
        return jeloltLoader.getEmployeePage(pageNumber, name);
    }

    /*public  List<Jelolt> findByFullNameIsContaining(String name) {
        return jeloltRepository.findByFullNameIsContaining(name);
    }*/
    public int getNumberOfPages() {
        return jeloltLoader.getNumberOfPages();
    }

}
