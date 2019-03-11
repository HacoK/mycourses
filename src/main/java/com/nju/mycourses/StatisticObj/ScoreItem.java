package com.nju.mycourses.StatisticObj;

public class ScoreItem {
    private Long scoreId;
    private Long curriculumId;
    private String courseName;
    private String semester;
    private String teacher;
    private String title;
    private String method;
    private Double result;

    public ScoreItem(Long scoreId, Long curriculumId, String courseName, String semester, String teacher, String title, String method, Double result) {
        this.scoreId = scoreId;
        this.curriculumId = curriculumId;
        this.courseName = courseName;
        this.semester = semester;
        this.teacher = teacher;
        this.title = title;
        this.method = method;
        this.result = result;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
