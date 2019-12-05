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

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Override
    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findById(long id);

    Page<Employee> findAll(Pageable pageable);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndTypeAndLevelAndLocation
         (Pageable pageable, String firstName, String lastName, String mail, JobType type, JobLevel level, Location location);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndType
            (Pageable pageable, String firstName, String lastName, String mail, JobType type);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndLevel
            (Pageable pageable, String firstName, String lastName, String mail, JobLevel level);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndLocation
            (Pageable pageable, String firstName, String lastName, String mail, Location location);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndLevelAndLocation
            (Pageable pageable, String firstName, String lastName, String mail, JobLevel level, Location location);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndTypeAndLocation
            (Pageable pageable, String firstName, String lastName, String mail, JobType type, Location location);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContainingAndTypeAndLevel
            (Pageable pageable, String firstName, String lastName, String mail, JobType type, JobLevel level);

   Page<Employee> findByFirstNameIsContainingAndLastNameIsContainingAndMailIsContaining
           (Pageable pageable, String firstName, String lastName, String mail);

}
