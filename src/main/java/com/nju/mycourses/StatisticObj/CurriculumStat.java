package com.nju.mycourses.StatisticObj;

public class CurriculumStat {
    private Long curriculumId;
    private Long teacherId;
    private Long courseId;
    private String courseName;
    private String description;
    private String semester;
    private String typeST;
    private String teacher;
    private String state;

    public CurriculumStat(Long curriculumId, Long teacherId, Long courseId, String courseName, String description, String semester, String typeST, String teacher, String state) {
        this.curriculumId = curriculumId;
        this.teacherId = teacherId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.semester = semester;
        this.typeST = typeST;
        this.teacher = teacher;
        this.state = state;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTypeST() {
        return typeST;
    }

    public void setTypeST(String typeST) {
        this.typeST = typeST;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
