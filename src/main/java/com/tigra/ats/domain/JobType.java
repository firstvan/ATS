package com.tigra.ats.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "type")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Job> jobs;
    @OneToMany(mappedBy = "type")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Employee> employees;

    public JobType(String name) {
        this.name = name;
    }
}
