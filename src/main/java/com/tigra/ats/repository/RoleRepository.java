package com.tigra.ats.repository;
import com.tigra.ats.domain.Role;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
		Role findByRole(String role);

		@Override
	    List<Role> findAll();
}