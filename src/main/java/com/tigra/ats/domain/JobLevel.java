package com.tigra.ats.domain;

import lombok.*;

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
    @OneToMany(mappedBy = "level")
    private List<Job> jobs;

    public JobLevel(String level) {
        this.level = level;
    }
}
