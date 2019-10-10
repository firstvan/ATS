package com.tigra.ats.repository;

import com.tigra.ats.domain.JobCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends CrudRepository<JobCategory, Long> {
}
