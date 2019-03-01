package com.nju.mycourses.util;

public class CurriculumCardAD {
    private Long curriculumId;
    private String courseName;
    private String teacherName;
    private String semesterYear;
    private String semesterSeason;
    private String schedule;
    private Integer restriction;

    public CurriculumCardAD(Long curriculumId, String courseName, String teacherName, String semesterYear, String semesterSeason, String schedule, Integer restriction) {
        this.curriculumId = curriculumId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.semesterYear = semesterYear;
        this.semesterSeason = semesterSeason;
        this.schedule = schedule;
        this.restriction = restriction;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
}
