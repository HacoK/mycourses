package com.nju.mycourses.service;

import com.nju.mycourses.DAO.ForumReplyRepository;
import com.nju.mycourses.DAO.ForumTopicRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.POJO.Prompt;
import com.nju.mycourses.entity.ForumReply;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TopicService {
    @Autowired
    ForumTopicRepository forumTopicRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ForumReplyRepository forumReplyRepository;

    public String getTopic(Long topicId){
        return forumTopicRepository.findById(topicId).get().getTopic();
    }
    public String getPostUser(Long topicId){
        Long userId=forumTopicRepository.findById(topicId).get().getUserId();
        return userRepository.findById(userId).get().getUserName();
    }
    public String getPostTime(Long topicId){
        LocalDateTime postTime=forumTopicRepository.findById(topicId).get().getReleaseTime();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(postTime);
    }
    public String getDescription(Long topicId){
        return forumTopicRepository.findById(topicId).get().getDescription().replaceAll("\n","<br>");
    }
    @Transient
    public JSONObject replyPost(Long topicId,String userName,String content,String releaseTime){
        Long userId=userRepository.findByUserName(userName).getUserId();
        Integer replyNum= Math.toIntExact(forumReplyRepository.countByTopicId(topicId));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(releaseTime,df);

        ForumReply forumReply=new ForumReply(topicId,userId,replyNum,content,ldt);
        forumReplyRepository.save(forumReply);

        Prompt prompt=new Prompt("Reply post successfully!");
        return new JSONObject(prompt);
    }
}
