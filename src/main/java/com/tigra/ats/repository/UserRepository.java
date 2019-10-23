package com.tigra.ats.repository;

import com.tigra.ats.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByFullNameIsContaining(String name);
    User findByEmail(String username);

    @Override
    List<User> findAll();
}