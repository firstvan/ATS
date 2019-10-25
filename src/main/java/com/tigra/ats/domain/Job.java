package com.tigra.ats.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

@Entity
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

    protected Job() {
    }

    public Job(JobType type, JobLevel level, Location location) {
        this.createdDate = LocalDate.now();
        this.location = location;
        this.type = type;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public JobLevel getLevel() {
        return level;
    }

    public void setLevel(JobLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(getLocation(), job.getLocation()) &&
                Objects.equals(getType(), job.getType()) &&
                Objects.equals(getLevel(), job.getLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getType(), getLevel());
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", location=" + location +
                ", type=" + type +
                ", level=" + level +
                '}';
    }
}
