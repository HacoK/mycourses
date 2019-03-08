package com.nju.mycourses.entity;

import com.nju.mycourses.enums.StType;

import javax.persistence.*;

@Entity
public class StInfo {
    @Id
    private Long userId;
    @Column(nullable = false, unique = true)
    private String studentId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StType typeST;

    public StInfo() {
    }

    public StInfo(Long userId, String studentId, StType typeST) {
        this.userId = userId;
        this.studentId = studentId;
        this.typeST = typeST;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public StType getTypeST() {
        return typeST;
    }

    public void setTypeST(StType typeST) {
        this.typeST = typeST;
    }
}
