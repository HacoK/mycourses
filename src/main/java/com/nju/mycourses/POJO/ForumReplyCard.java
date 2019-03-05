package com.nju.mycourses.POJO;

public class ForumReplyCard {
    private Integer replyNum;
    private String replyUser;
    private String replyTime;
    private String content;

    public ForumReplyCard(Integer replyNum, String replyUser, String replyTime, String content) {
        this.replyNum = replyNum;
        this.replyUser = replyUser;
        this.replyTime = replyTime;
        this.content = content;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(String replyUser) {
        this.replyUser = replyUser;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
