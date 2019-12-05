package com.tigra.ats.repository;

import com.tigra.ats.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    @Override
    Location save(Location s);

    @Override
    List<Location> findAll();

    Location findById(long id);

    Optional<Location> findByCity(String city);
}
