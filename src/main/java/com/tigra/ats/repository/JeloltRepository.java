package com.tigra.ats.repository;

import com.tigra.ats.domain.Jelolt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface JeloltRepository extends CrudRepository<Jelolt, Long> {
    Page<Jelolt> findByFullNameIsContaining(Pageable pageable, String name);
    //List<Jelolt> findByFullNameIsContaining(String name);
}
