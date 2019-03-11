package com.nju.mycourses.StatisticObj;

public class AssignmentItem {
    private Long assignmentId;
    private String courseName;
    private String semester;
    private String typeST;
    private String title;
    private String startline;
    private String deadline;
    private String size;
    private String type;
    private String submitted;

    public AssignmentItem(Long assignmentId, String courseName, String semester, String typeST, String title, String startline, String deadline, String size, String type, String submitted) {
        this.assignmentId = assignmentId;
        this.courseName = courseName;
        this.semester = semester;
        this.typeST = typeST;
        this.title = title;
        this.startline = startline;
        this.deadline = deadline;
        this.size = size;
        this.type = type;
        this.submitted = submitted;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTypeST() {
        return typeST;
    }

    public void setTypeST(String typeST) {
        this.typeST = typeST;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartline() {
        return startline;
    }

    public void setStartline(String startline) {
        this.startline = startline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }
}
