package com.nju.mycourses.StatisticObj;

public class StudentStat {
    private Long userId;
    private String studentId;
    private String studentName;
    private String studentType;
    private Integer currentCourse;
    private Integer totalCourse;
    private String studentEmail;
    private String activation;

    public StudentStat(Long userId, String studentId, String studentName, String studentType, Integer currentCourse, Integer totalCourse, String studentEmail, String activation) {
        this.userId = userId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentType = studentType;
        this.currentCourse = currentCourse;
        this.totalCourse = totalCourse;
        this.studentEmail = studentEmail;
        this.activation = activation;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public Integer getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Integer currentCourse) {
        this.currentCourse = currentCourse;
    }

    public Integer getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(Integer totalCourse) {
        this.totalCourse = totalCourse;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }
}
