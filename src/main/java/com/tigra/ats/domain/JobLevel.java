package com.tigra.ats.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class JobLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String level;
    @OneToMany(mappedBy = "level")
    private List<Job> jobs;

    protected JobLevel() {
    }

    public JobLevel(String level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobLevel jobLevel = (JobLevel) o;
        return getLevel().equals(jobLevel.getLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLevel());
    }

    @Override
    public String toString() {
        return "JobLevel{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}
