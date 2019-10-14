package com.tigra.ats.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobName;
    @OneToMany(mappedBy = "type")
    private List<Job> jobs;

    protected JobType() {
    }

    public JobType(String jobName) {
        this.jobName = jobName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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
        JobType jobType = (JobType) o;
        return getJobName().equals(jobType.getJobName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobName());
    }

    @Override
    public String toString() {
        return jobName;
    }
}
