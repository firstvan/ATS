package com.tigra.ats.service.entityhandler;

import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobLoader {

    private JobPropertyHandler jobPropertyHandler;

    @Autowired
    public JobLoader(JobPropertyHandler jobPropertyHandler) {
        this.jobPropertyHandler = jobPropertyHandler;
    }

    public List<JobType> getTypes() {
        return jobPropertyHandler.findAllType();
    }

    public List<JobLevel> getLevels() {
        return jobPropertyHandler.findAllLevel();
    }

    public List<Location> getLocations() {
        return jobPropertyHandler.findAllLocation();
    }
}
