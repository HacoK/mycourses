package com.nju.mycourses.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ForumReply {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long replyId;
    @Column(nullable = false)
    private Long topicId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Integer replyNum;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalDateTime releaseTime;

    public ForumReply() {
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
    }
}
