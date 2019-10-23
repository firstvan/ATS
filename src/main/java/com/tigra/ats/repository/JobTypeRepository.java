package com.tigra.ats.repository;

import com.tigra.ats.domain.JobType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobTypeRepository extends CrudRepository<JobType, Long> {
    @Override
    JobType save(JobType type);

    @Override
    List<JobType> findAll();

    Optional<JobType> findByName(String name);
}
