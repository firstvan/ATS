package com.tigra.ats.repository;

import com.tigra.ats.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    Employee save(Employee s);
}
