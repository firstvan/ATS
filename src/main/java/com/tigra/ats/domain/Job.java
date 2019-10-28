package com.tigra.ats.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Job(JobType type, JobLevel level, Location location) {
        this.createdDate = LocalDate.now();
        this.location = location;
        this.type = type;
        this.level = level;
    }
}
