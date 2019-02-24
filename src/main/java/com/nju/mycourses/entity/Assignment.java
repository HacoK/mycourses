package com.nju.mycourses.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long assignmentId;
    @Column(nullable = false)
    private String curriculum_id;
    @Column(nullable = false)
    private LocalDate startline;
    @Column(nullable = false)
    private LocalDate deadline;
    @Column
    private Integer size;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private String tc_path;
    @Column
    private String st_dir;

    public Assignment() {
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public LocalDate getStartline() {
        return startline;
    }

    public void setStartline(LocalDate startline) {
        this.startline = startline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTc_path() {
        return tc_path;
    }

    public void setTc_path(String tc_path) {
        this.tc_path = tc_path;
    }

    public String getSt_dir() {
        return st_dir;
    }

    public void setSt_dir(String st_dir) {
        this.st_dir = st_dir;
    }
}
