package com.nju.mycourses.StatisticObj;

public class SelectItem {
    private Long recId;
    private Long curriculumId;
    private String courseName;
    private String semester;
    private String schedule;
    private String teacher;
    private String result;

    public SelectItem(Long recId, Long curriculumId, String courseName, String semester, String schedule, String teacher, String result) {
        this.recId = recId;
        this.curriculumId = curriculumId;
        this.courseName = courseName;
        this.semester = semester;
        this.schedule = schedule;
        this.teacher = teacher;
        this.result = result;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
