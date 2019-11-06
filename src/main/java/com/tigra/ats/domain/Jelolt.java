package com.tigra.ats.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name="Jeloltek" )
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Jelolt {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column( unique=true, nullable=false )
        private String email;
        @Column( nullable=false )
        private String fullName;
        private String pozNev;
        private Boolean statusz;
        private String szint;
        private String telephely;

}
