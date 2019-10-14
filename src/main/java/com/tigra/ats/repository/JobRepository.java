package com.tigra.ats.repository;

import com.tigra.ats.domain.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    @Override
    List<Job> findAll();

    @Override
    void deleteById(Long id);

    @Override
    Job save(Job job);
}
