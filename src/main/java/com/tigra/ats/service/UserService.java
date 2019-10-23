package com.tigra.ats.service;

import com.tigra.ats.domain.Role;
import com.tigra.ats.domain.User;
import com.tigra.ats.repository.RoleRepository;
import com.tigra.ats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public List<User> findByFullNameIsContaining(String name) {
		return userRepository.findByFullNameIsContaining("%"+name+"%");
	}

	public User findById(long id) {
		return userRepository.findById(id).get();
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public List<User> findAllUserExceptAdmin() {
		return userRepository.findAll();
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(user);
	}

	private User findByEmail(String username) {
		return userRepository.findByEmail(username);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

	public Role findRoleByName(String role) {
		return roleRepository.findByRole(role);
	}
}