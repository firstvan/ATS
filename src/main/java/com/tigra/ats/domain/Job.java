package com.tigra.ats.domain;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
    @ManyToMany(mappedBy = "jobs", fetch = FetchType.EAGER)
    private List<Employee> employees = new ArrayList<>();

    public Job(JobType type, JobLevel level, Location location) {
        this.createdDate = LocalDate.now();
        this.location = location;
        this.type = type;
        this.level = level;
        this.displayStatus = false;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", location=" + location +
                ", type=" + type +
                ", level=" + level +
               "}";
    }
}
