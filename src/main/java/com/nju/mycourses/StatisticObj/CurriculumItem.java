package com.nju.mycourses.StatisticObj;

public class CurriculumItem {
    private Long curriculumId;
    private Long courseId;
    private String courseName;
    private String description;
    private String semester;
    private String typeST;
    private Integer restriction;
    private Integer selected;
    private String state;

    public CurriculumItem(Long curriculumId, Long courseId, String courseName, String description, String semester, String typeST, Integer restriction, Integer selected, String state) {
        this.curriculumId = curriculumId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.semester = semester;
        this.typeST = typeST;
        this.restriction = restriction;
        this.selected = selected;
        this.state = state;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
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

    public Integer getRestriction() {
        return restriction;
    }

    public void setRestriction(Integer restriction) {
        this.restriction = restriction;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
