package com.tigra.ats.domain;


import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity

@Table( name = "roles" )
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	
	private String role;

	@OneToMany(mappedBy = "role")
	private List<User> users;

	public Role(){}
	
	public Role(String role){
		this.role=role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Role role1 = (Role) o;
		return Objects.equals(getRole(), role1.getRole());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRole());
	}

	@Override
	public String toString() {
		return this.role.substring(5);
	}
}
