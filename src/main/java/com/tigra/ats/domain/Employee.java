package com.tigra.ats.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @NotNull
    @NotEmpty
    private String birthName;
    @NotNull
    @NotEmpty
    private String birthPlace;
    @NotNull
    @NotEmpty
    private String mother;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDay;
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String mail;
    private String status;
    private String phoneNumber;
    @ManyToOne
    private JobType type;
    @ManyToOne
    private JobLevel level;
    @ManyToOne
    private Location location;
    @OneToOne
    private DBFile CV;
    @ManyToMany(mappedBy = "employees")
    private Set<Job> jobs;
}
