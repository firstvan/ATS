package com.tigra.ats.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @JsonIgnore
    private JobType type;
    @ManyToOne
    @JsonIgnore
    private JobLevel level;
    @ManyToOne
    @JsonIgnore
    private Location location;
    @OneToOne
    @JsonIgnore
    private DBFile CV;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "registered_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "registration_id"))
    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();


    public void addJob(Job job) {
        jobs.add(job);
        job.getEmployees().add(this);
    }
}
