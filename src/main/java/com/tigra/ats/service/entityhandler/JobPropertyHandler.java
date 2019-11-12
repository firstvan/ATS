package com.tigra.ats.service.entityhandler;

import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.JobLevelRepository;
import com.tigra.ats.repository.JobTypeRepository;
import com.tigra.ats.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JobPropertyHandler {
    private JobTypeRepository jobTypeRepository;
    private JobLevelRepository jobLevelRepository;
    private LocationRepository locationRepository;

    @Autowired
    public JobPropertyHandler(JobTypeRepository jobTypeRepository, JobLevelRepository jobLevelRepository, LocationRepository locationRepository) {
        this.jobTypeRepository = jobTypeRepository;
        this.jobLevelRepository = jobLevelRepository;
        this.locationRepository = locationRepository;
    }

    public Optional<JobType> getType(String type) {
        return jobTypeRepository.findByName(type);
    }

    public Optional<JobLevel> getLevel(String level) {
        return jobLevelRepository.findByLevel(level);
    }

    public Optional<Location> getLocation(String city) {
        return locationRepository.findByCity(city);
    }

    public void saveType(JobType type) {
        jobTypeRepository.save(type);
    }

    public void saveLevel(JobLevel level) {
        jobLevelRepository.save(level);
    }

    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    public List<JobType> findAllType() {
        return jobTypeRepository.findAll();
   }

    public List<JobLevel> findAllLevel() {
        return jobLevelRepository.findAll();
   }

    public List<Location> findAllLocation() {
        return locationRepository.findAll();
   }
}
