package com.tigra.ats.service;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.service.entityhandler.JobRegister;
import com.tigra.ats.service.entityhandler.JobLoader;
import com.tigra.ats.service.paginate.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private JobLoader jobLoader;
    private JobRegister jobRegister;
    private Paginator paginator;

    @Autowired
    public JobService(JobLoader jobLoader, JobRegister jobRegister, @Qualifier("jobPaginator") Paginator paginator) {
        this.jobLoader = jobLoader;
        this.jobRegister = jobRegister;
        this.paginator = paginator;
    }

    public void saveAvailableJobProperties(String type, String level, String city) {
        jobRegister.saveType(type);
        jobRegister.saveLevel(level);
        jobRegister.saveLocation(city);
    }

    public void createJob(String type, String level, String city) {
        jobRegister.createJob(type, level, city);
    }

    public void deleteJob(Long id) {
        jobRegister.deleteJob(id);
    }

    public Page<Job> getAvailableJobs(int pageNumber) {
        paginator.setMaxItemInOnePage(2);
        return paginator.getPage(pageNumber);
    }

    public List<JobType> getTypes() {
        return jobLoader.getTypes();
    }

    public List<JobLevel> getLevels() {
        return jobLoader.getLevels();
    }

    public List<Location> getLocations() {
        return jobLoader.getLocations();
    }
}
