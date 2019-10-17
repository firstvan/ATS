package com.tigra.ats.repository;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    @Override
    List<Job> findAll();

    @Override
    void deleteById(Long id);

    @Override
    Job save(Job job);

    Optional<Job> findByTypeAndLevelAndLocation(JobType type, JobLevel level, Location location);
}
