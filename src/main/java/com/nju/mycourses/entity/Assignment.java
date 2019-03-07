package com.nju.mycourses.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long assignmentId;
    @Column(nullable = false)
    private String curriculum_id;
    @Column(nullable = false)
    private LocalDateTime startline;
    @Column(nullable = false)
    private LocalDateTime deadline;
    @Column
    private Integer size;
    @Column
    private String type;
    @Column
    private String title;
    @Column
    private String content;
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

    public LocalDateTime getStartline() {
        return startline;
    }

    public void setStartline(LocalDateTime startline) {
        this.startline = startline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
