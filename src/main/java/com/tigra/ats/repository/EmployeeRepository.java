package com.tigra.ats.repository;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.domain.Jelolt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    Employee save(Employee s);

    Page<Employee> findByFirstNameIsContaining(Pageable pageable, String name);

}
