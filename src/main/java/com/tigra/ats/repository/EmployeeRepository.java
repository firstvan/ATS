package com.tigra.ats.repository;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    Employee save(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(long id);

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContaining
            (Pageable pageable, String firstName, String lastName, String mail);

}
