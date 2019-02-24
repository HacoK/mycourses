package com.nju.mycourses.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Forum_reply {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long replyId;
    @Column(nullable = false)
    private String topic_id;
    @Column(nullable = false)
    private String user_id;
    @Column(nullable = false)
    private Integer replyNum;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDate releaseTime;

    public Forum_reply() {
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDate releaseTime) {
        this.releaseTime = releaseTime;
    }
}
