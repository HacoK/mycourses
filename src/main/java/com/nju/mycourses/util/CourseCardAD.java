package com.nju.mycourses.util;

public class CourseCardAD {

    private Long courseId;
    private String courseName;
    private String description;
    private String teacherName;

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public CourseCardAD(Long courseId, String courseName, String description, String teacherName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.teacherName = teacherName;
    }
}
