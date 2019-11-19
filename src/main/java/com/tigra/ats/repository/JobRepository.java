package com.tigra.ats.repository;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
    Page<Job> findAll(Pageable pageable);

    @Override
    void deleteById(Long id);

    @Override
    Job save(Job job);

    @Override
    Optional<Job> findById(Long id);

    Page<Job> findAllByDisplayStatus(Pageable pageable, boolean status);

    Optional<Job> findByTypeAndLevelAndLocation(JobType type, JobLevel level, Location location);
}
