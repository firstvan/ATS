package com.tigra.ats.repository;

import com.tigra.ats.domain.JobLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobLevelRepository extends CrudRepository<JobLevel, Long> {
    @Override
    JobLevel save(JobLevel level);

    @Override
    List<JobLevel> findAll();

    Optional<JobLevel> findByLevel(String level);
}
