package com.nju.mycourses.entity;

import com.nju.mycourses.enums.ScoreType;

import javax.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long scoreId;
    @Column(nullable = false)
    private Long curriculumId;
    @Column(nullable = false)
    private String scoreExcel;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScoreType scoreType;
}
