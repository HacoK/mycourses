package com.nju.mycourses.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ForumTopic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long topicId;
    @Column(nullable = false)
    private Long courseId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String topic;
    @Lob
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime releaseTime;

    public ForumTopic() {
    }

    public ForumTopic(Long courseId, Long userId, String topic, String description, LocalDateTime releaseTime) {
        this.courseId = courseId;
        this.userId = userId;
        this.topic = topic;
        this.description = description;
        this.releaseTime = releaseTime;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDateTime releaseTime) {
        this.releaseTime = releaseTime;
    }
}
