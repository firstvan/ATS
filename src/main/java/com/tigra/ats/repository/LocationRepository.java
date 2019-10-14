package com.tigra.ats.repository;

import com.tigra.ats.domain.Location;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends CrudRepository<Location, Long> {
    @Override
    Location save(Location s);

    @Override
    List<Location> findAll();

    Optional<Location> findByCity(String city);
}
