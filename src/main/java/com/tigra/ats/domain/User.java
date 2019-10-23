package com.tigra.ats.domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table( name="users" )
public class User {

	@Id @GeneratedValue
	private Long id;
	
	@Column( unique=true, nullable=false )
	private String email;
	
	@Column( nullable=false )
	private String password;
	
	private String fullName;

	private String username;


    @ManyToOne(optional = false)
    private Role role;

	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	public void addRole(String roleName) {
			this.role = new Role(roleName);
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(getEmail(), user.getEmail()) &&
				Objects.equals(getFullName(), user.getFullName()) &&
				Objects.equals(getRole(), user.getRole());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), getFullName(), getRole());
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

	public void deleteRole() {
		this.role=null;
	}
}