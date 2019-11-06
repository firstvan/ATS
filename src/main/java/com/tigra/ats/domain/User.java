package com.tigra.ats.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table( name="users" )
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column( length=50, unique=true )
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