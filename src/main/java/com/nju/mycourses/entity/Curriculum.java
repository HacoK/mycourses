package com.nju.mycourses.entity;

import com.nju.mycourses.enums.ScoreType;

import javax.persistence.*;

@Entity
public class Curriculum {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long curriculumId;
    @Column(nullable = false)
    private Long courseId;
    @Column(nullable = false)
    private String semesterYear; //年份
    @Column(nullable = false)
    private String semesterSeason; //春/秋
    @Column(nullable = false)
    private String schedule;
    @Column(nullable = false)
    private Integer restriction;
    //-1:已否决,0:待审批,1:已通过,2:已结课,3:已开课
    @Column(nullable = false)
    private Integer approved;
    @Column(nullable = false)
    private String scoreExcel;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScoreType scoreType;

    public Curriculum() {
    }

    public Curriculum(Long courseId, String semesterYear, String semesterSeason, String schedule, Integer restriction, Integer approved, String scoreExcel, ScoreType scoreType) {
        this.courseId = courseId;
        this.semesterYear = semesterYear;
        this.semesterSeason = semesterSeason;
        this.schedule = schedule;
        this.restriction = restriction;
        this.approved = approved;
        this.scoreExcel = scoreExcel;
        this.scoreType = scoreType;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
