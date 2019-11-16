package com.tigra.ats.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private JobType type;
    @ManyToOne
    @NotNull
    private JobLevel level;
    @ManyToOne
    @NotNull
    private Location location;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "registered_employee",
                joinColumns = @JoinColumn(name = "registration_id"),
                inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees;
}
