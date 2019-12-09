package com.tigra.ats.service.entityhandler;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.JobRepository;
import com.tigra.ats.service.exception.CannotCreateJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Pozíciók({@link Job}), illetve a pozíciókhoz szükséges tulajdonságok({@link JobLevel}, {@link JobType},
 * {@link Location}) mentését valósítja meg.
 */
@Component
public class JobRegister {
    private JobPropertyHandler jobPropertyHandler;
    private JobRepository jobRepository;

    @Autowired
    public JobRegister(JobPropertyHandler jobPropertyHandler, JobRepository jobRepository) {
        this.jobPropertyHandler = jobPropertyHandler;
        this.jobRepository = jobRepository;
    }

    public void saveType(String typeName) {
        if (!typeName.isEmpty())
            jobPropertyHandler.saveType(new JobType(typeName));
    }

    public void saveLevel(String levelName) {
        if(!levelName.isEmpty())
            jobPropertyHandler.saveLevel(new JobLevel(levelName));
    }

    public void saveLocation(String city) {
        if(!city.isEmpty())
            jobPropertyHandler.saveLocation(new Location(city));
    }

    public boolean createJobProperties(String typeName, String levelName, String location) {
        Optional<JobType> optionalType = jobPropertyHandler.getType(typeName);
        Optional<JobLevel> optionalLevel = jobPropertyHandler.getLevel(levelName);
        Optional<Location> optionalLocation = jobPropertyHandler.getLocation(location);

        if(optionalType.isPresent() || optionalLevel.isPresent() || optionalLocation.isPresent()) {
            return false;
        }
        else {
            saveType(typeName);
            saveLevel(levelName);
            saveLocation(location);
            return true;
        }
    }

    public Optional<Job> saveJob(Job job, boolean isDisplayed) {
        Job createdJob = jobRepository
                .findByTypeAndLevelAndLocation(job.getType(), job.getLevel(), job.getLocation())
                .orElseGet(() -> new Job(job.getType(), job.getLevel(), job.getLocation()));
        if(createdJob.isDisplayStatus()) {
            return Optional.empty();
        }
        else {
            createdJob.setDisplayStatus(isDisplayed);
            createdJob.setCreatedDate(LocalDate.now());
            createdJob = jobRepository.save(createdJob);
            return Optional.of(createdJob);
        }
    }

    public void deleteJob(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setDisplayStatus(false);
            jobRepository.save(job);
        }
    }
}
