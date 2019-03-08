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
    private Long curriculumId;
    @Column(nullable = false)
    private LocalDateTime startline;
    @Column(nullable = false)
    private LocalDateTime deadline;
    @Column
    private Integer size;
    @Column
    private String unit;
    @Column
    private String type;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private Boolean attachment;
    @Column
    private String rootDir;

    public Assignment() {
    }

    public Assignment(Long curriculumId, LocalDateTime startline, LocalDateTime deadline, Integer size, String unit, String type, String title, String content, Boolean attachment, String rootDir) {
        this.curriculumId = curriculumId;
        this.startline = startline;
        this.deadline = deadline;
        this.size = size;
        this.unit = unit;
        this.type = type;
        this.title = title;
        this.content = content;
        this.attachment = attachment;
        this.rootDir = rootDir;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }
}
