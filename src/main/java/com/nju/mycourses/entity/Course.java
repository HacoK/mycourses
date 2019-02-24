package com.nju.mycourses.entity;

import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long courseId;
    @Column(nullable = false)
    private String courseName;
    @Lob
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String courseware;
    @Column(nullable = false)
    private Integer approved;
    @Column(nullable = false)
    private Long teacher_id;

    public Course() {
    }

    public Course(String courseName, String description, String courseware, Integer approved, Long teacher_id) {
        this.courseName = courseName;
        this.description = description;
        this.courseware = courseware;
        this.approved = approved;
        this.teacher_id = teacher_id;
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

    public String getCourseware() {
        return courseware;
    }

    public void setCourseware(String courseware) {
        this.courseware = courseware;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }
}
