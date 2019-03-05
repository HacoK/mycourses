package com.nju.mycourses.POJO;

public class ForumTopicCard {
    private Long topicId;
    private String topic;
    private String postUser;
    private String postTime;
    private Integer replyNum;
    private String replyUserLS;
    private String replyTimeLS;

    public ForumTopicCard(Long topicId, String topic, String postUser, String postTime, Integer replyNum, String replyUserLS, String replyTimeLS) {
        this.topicId = topicId;
        this.topic = topic;
        this.postUser = postUser;
        this.postTime = postTime;
        this.replyNum = replyNum;
        this.replyUserLS = replyUserLS;
        this.replyTimeLS = replyTimeLS;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getReplyUserLS() {
        return replyUserLS;
    }

    public void setReplyUserLS(String replyUserLS) {
        this.replyUserLS = replyUserLS;
    }

    public String getReplyTimeLS() {
        return replyTimeLS;
    }

    public void setReplyTimeLS(String replyTimeLS) {
        this.replyTimeLS = replyTimeLS;
    }
}
