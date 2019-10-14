package com.tigra.ats.service;

import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JobPropertyContainer {
    private List<JobType> types;
    private List<JobLevel> levels;
    private List<Location> locations;

    @Autowired
    public JobPropertyContainer(JobPropertyHandler jobPropertyHandler) {
        this.types = jobPropertyHandler.findAllType();
        this.levels = jobPropertyHandler.findAllLevel();
        this.locations = jobPropertyHandler.findAllLocation();
    }

    public List<JobType> getTypes() {
        return types;
    }

    public List<JobLevel> getLevels() {
        return levels;
    }

    public List<Location> getLocations() {
        return locations;
    }
}
