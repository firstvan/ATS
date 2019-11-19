package com.tigra.ats.repository;

import com.tigra.ats.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    Employee save(Employee employee);

    Page<Employee> findByFirstNameIsContaining(Pageable pageable, String name);

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByLastNameIsContaining(Pageable pageable, String name);
}
