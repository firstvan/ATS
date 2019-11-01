package com.tigra.ats.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String birthName;
    private String birthPlace;
    private String mother;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDay;
    private String mail;
    private String status;
    @ManyToOne
    private JobType type;
    @ManyToOne
    private JobLevel level;
    @ManyToOne
    private Location location;
    @Lob
    private byte[] CV;
}
