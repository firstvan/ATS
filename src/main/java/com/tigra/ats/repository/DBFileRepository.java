package com.tigra.ats.repository;

import com.tigra.ats.domain.DBFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends CrudRepository<DBFile, Long> {
    @Override
    DBFile save(DBFile file);
}
