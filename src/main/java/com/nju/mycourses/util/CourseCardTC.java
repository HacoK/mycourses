package com.nju.mycourses.util;

public class CourseCardTC {
    private Long courseId;
    private String courseName;
    private String description;
    private String state;

    public CourseCardTC(Long courseId, String courseName, String description, String state) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.state = state;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
