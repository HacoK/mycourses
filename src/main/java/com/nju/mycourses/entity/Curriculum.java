package com.nju.mycourses.entity;

import com.nju.mycourses.enums.ScoreType;

import javax.persistence.*;

@Entity
public class Curriculum {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long curriculumId;
    @Column(nullable = false)
    private Long course_id;
    @Column(nullable = false)
    private String semesterYear; //年份
    @Column(nullable = false)
    private String semesterSeason; //春/秋
    @Column(nullable = false)
    private String schedule;
    @Column(nullable = false)
    private Integer restriction;
    @Column(nullable = false)
    private Integer approved;
    @Column(nullable = false)
    private String scoreExcel;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScoreType scoreType;

    public Curriculum() {
    }

    public Curriculum(Long course_id, String semesterYear,String semesterSeason,String schedule, Integer restriction, Integer approved) {
        this.course_id = course_id;
        this.semesterYear = semesterYear;
        this.semesterSeason=semesterSeason;
        this.schedule = schedule;
        this.restriction = restriction;
        this.approved = approved;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getSemesterYear() {
        return semesterYear;
    }

    public void setSemesterYear(String semesterYear) {
        this.semesterYear = semesterYear;
    }

    public String getSemesterSeason() {
        return semesterSeason;
    }

    public void setSemesterSeason(String semesterSeason) {
        this.semesterSeason = semesterSeason;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getRestriction() {
        return restriction;
    }

    public void setRestriction(Integer restriction) {
        this.restriction = restriction;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public String getScoreExcel() {
        return scoreExcel;
    }

    public void setScoreExcel(String scoreExcel) {
        this.scoreExcel = scoreExcel;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }

    public void setScoreType(ScoreType scoreType) {
        this.scoreType = scoreType;
    }
}
