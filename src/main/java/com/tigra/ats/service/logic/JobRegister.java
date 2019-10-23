package com.tigra.ats.service.logic;

import com.tigra.ats.domain.Job;
import com.tigra.ats.domain.JobLevel;
import com.tigra.ats.domain.JobType;
import com.tigra.ats.domain.Location;
import com.tigra.ats.repository.JobRepository;
import com.tigra.ats.service.exception.CannotCreateJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /**
     * Egy új pozíció típust ment el az adatbázisba, ha az még nem létezik.
     * Ha létezik, nem menti el újra az adott típust.
     * @param typeName a pozíció megnevezése
     */
    public void saveType(String typeName) {
        if(!typeName.isEmpty()) {
            Optional<JobType> jobType = jobPropertyHandler.getType(typeName);
            if(!jobType.isPresent())
                jobPropertyHandler.saveType(new JobType(typeName));
        }
    }

    /**
     * Egy új pozíció szintet ment el az adatbázisba, ha az még nem létezik.
     * Ha létezik, nem menti el újra az adott szintet.
     * @param levelName a szint megnevezése
     */
    public void saveLevel(String levelName) {
        if(!levelName.isEmpty()) {
            Optional<JobLevel> jobLevel = jobPropertyHandler.getLevel(levelName);
            if(!jobLevel.isPresent())
                jobPropertyHandler.saveLevel(new JobLevel(levelName));
        }
    }

    /**
     * Egy új telephelyet ment el az adatbázisba, ha az még nem létezik.
     * Ha létezik, nem menti el újra az adott típust.
     * @param city a város neve, ahol a telephely megtalálható
     */
    public void saveLocation(String city) {
        if(!city.isEmpty()) {
            Optional<Location> jobLocation = jobPropertyHandler.getLocation(city);
            if(!jobLocation.isPresent())
                jobPropertyHandler.saveLocation(new Location(city));
        }
    }

    /**
     * Meghírdet egy állást az aktuális dátummal és a paraméterekként átadott tulajdonságokkal.
     *
     * @param typeName a pozíció típusának neve
     * @param level a pozíció típusának a szintje
     * @param city a város, ahol a telephely található
     * @throws CannotCreateJob ha nem léteznek az adatbázisban a megadott tulajdonságok
     */
    public void createJob(String typeName, String level, String city) {
        Optional<JobType> jobType = jobPropertyHandler.getType(typeName);
        Optional<JobLevel> jobLevel = jobPropertyHandler.getLevel(level);
        Optional<Location> location = jobPropertyHandler.getLocation(city);

        if(jobType.isPresent() && jobLevel.isPresent() && location.isPresent()) {
            Job createdJob = jobRepository
                    .findByTypeAndLevelAndLocation(jobType.get(), jobLevel.get(), location.get())
                    .orElseGet(() -> new Job(jobType.get(), jobLevel.get(), location.get(), LocalDate.now()));
            jobRepository.save(createdJob);
        }
        else
            throw new CannotCreateJob("Properties not found!");
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
