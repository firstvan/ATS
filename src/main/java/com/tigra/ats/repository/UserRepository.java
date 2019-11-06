package com.tigra.ats.repository;

import com.tigra.ats.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String username);
    User findByUsername(String username);
    List<User> findAllByFullNameContaining(String e);
    @Override
    List<User> findAll();
}
