package com.nju.mycourses.POJO;

public class AssignmentCard {
    private Long id;
    private String title;
    private Integer submitNum;
    private Integer total;

    public AssignmentCard(Long id, String title, Integer submitNum, Integer total) {
        this.id = id;
        this.title = title;
        this.submitNum = submitNum;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
