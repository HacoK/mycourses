package com.nju.mycourses.entity;

import javax.persistence.*;

@Entity
public class CSelecRec {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long recordId;
    @Column(nullable = false)
    private String student_id;
    @Column(nullable = false)
    private String curriculum_id;
    @Column(nullable = false)
    private Boolean state;

    public CSelecRec() {
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(String curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
