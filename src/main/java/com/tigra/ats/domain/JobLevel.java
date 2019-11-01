package com.tigra.ats.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String level;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "level")
    private List<Job> jobs;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "level")
    private List<Employee> employees;

    public JobLevel(String level) {
        this.level = level;
    }
}
