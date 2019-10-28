package com.tigra.ats.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@Entity
@Table( name = "roles" )
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String role;

	@OneToMany(mappedBy = "role")
	private List<User> users;

	public Role(String role){
		this.role=role;
	}

	@Override
	public String toString() {
		return this.role.substring(5);
	}
}
