package com.kakaopay.apisrv.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Attraction {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "programName")
    @Getter
    @Setter
    private String programName;

    @Column(name = "theme")
    @Getter
    @Setter
    private String theme;

    @Column(name = "location")
    @Getter
    @Setter
    private String location;

    @Lob
    @Column(name = "programBrief")
    @Getter
    @Setter
    private String programBrief;

    @Lob
    @Column(name = "programDetail")
    @Getter
    @Setter
    private String programDetail;
}
