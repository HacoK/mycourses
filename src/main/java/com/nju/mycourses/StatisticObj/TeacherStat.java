package com.nju.mycourses.StatisticObj;

public class TeacherStat {
    private Long userId;
    private String teacherName;
    private Integer courseNum;
    private Integer currentCurriculum;
    private Integer totalCurriculum;
    private Integer assignmentNum;
    private Integer scoreNum;
    private String teacherEmail;
    private String activation;

    public TeacherStat(Long userId, String teacherName, Integer courseNum, Integer currentCurriculum, Integer totalCurriculum, Integer assignmentNum, Integer scoreNum, String teacherEmail, String activation) {
        this.userId = userId;
        this.teacherName = teacherName;
        this.courseNum = courseNum;
        this.currentCurriculum = currentCurriculum;
        this.totalCurriculum = totalCurriculum;
        this.assignmentNum = assignmentNum;
        this.scoreNum = scoreNum;
        this.teacherEmail = teacherEmail;
        this.activation = activation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public Integer getCurrentCurriculum() {
        return currentCurriculum;
    }

    public void setCurrentCurriculum(Integer currentCurriculum) {
        this.currentCurriculum = currentCurriculum;
    }

    public Integer getTotalCurriculum() {
        return totalCurriculum;
    }

    public void setTotalCurriculum(Integer totalCurriculum) {
        this.totalCurriculum = totalCurriculum;
    }

    public Integer getAssignmentNum() {
        return assignmentNum;
    }

    public void setAssignmentNum(Integer assignmentNum) {
        this.assignmentNum = assignmentNum;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }
}
