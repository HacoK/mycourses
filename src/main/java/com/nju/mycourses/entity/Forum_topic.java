package com.nju.mycourses.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Forum_topic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long topicId;
    @Column(nullable = false)
    private String course_id;
    @Column(nullable = false)
    private String user_id;
    @Column(nullable = false)
    private String topic;
    @Lob
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate releaseTime;

    public Forum_topic() {
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public LocalDate getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(LocalDate releaseTime) {
        this.releaseTime = releaseTime;
    }
}
