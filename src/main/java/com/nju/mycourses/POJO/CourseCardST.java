package com.nju.mycourses.POJO;

public class CourseCardST {
    private Long curriculumId;
    private String courseName;
    private String teacherName;
    private String semesterYear;
    private String semesterSeason;
    private String schedule;
    private String state;

    public CourseCardST(Long curriculumId, String courseName, String teacherName, String semesterYear, String semesterSeason, String schedule, String state) {
        this.curriculumId = curriculumId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.semesterYear = semesterYear;
        this.semesterSeason = semesterSeason;
        this.schedule = schedule;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
