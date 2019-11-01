package com.tigra.ats.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    @OneToMany(mappedBy = "location")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Job> jobs;
    @OneToMany(mappedBy = "location")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Employee> employees;

    public Location(String city) {
        this.city = city;
    }
}
