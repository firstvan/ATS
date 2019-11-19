package com.tigra.ats.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDate createdDate;
    @ManyToOne
    private Location location;
    @ManyToOne
    private JobType type;
    @ManyToOne
    private JobLevel level;
    private boolean displayStatus;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "registered_employee",
            joinColumns = @JoinColumn(name = "registration_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees;

    public Job(JobType type, JobLevel level, Location location) {
        this.createdDate = LocalDate.now();
        this.location = location;
        this.type = type;
        this.level = level;
        this.displayStatus = false;
    }
}
