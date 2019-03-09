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
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScoreType scoreType;
    @Column(nullable = false)
    private String excelPath;

    public Score() {
    }

    public Score(Long curriculumId, String title, ScoreType scoreType, String excelPath) {
        this.curriculumId = curriculumId;
        this.title = title;
        this.scoreType = scoreType;
        this.excelPath=excelPath;
    }

    public Long getScoreId() {
        return scoreId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }
}
