package com.tigra.ats.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name="users" )
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column( unique=true, nullable=false )
	private String email;
	@Column( nullable=false )
	private String password;
	private String fullName;
	private String username;
    @ManyToOne(optional = false)
    private Role role;

	public void deleteRole() {
		this.role=null;
	}
}