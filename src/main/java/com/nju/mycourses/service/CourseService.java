package com.nju.mycourses.service;

import com.nju.mycourses.DAO.*;
import com.nju.mycourses.POJO.ForumTopicCard;
import com.nju.mycourses.POJO.Prompt;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.ForumReply;
import com.nju.mycourses.entity.ForumTopic;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.POJO.CourseCardTC;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    ForumTopicRepository forumTopicRepository;
    @Autowired
    ForumReplyRepository forumReplyRepository;

    public void createCourse(String userName,String courseName,String description){
        User user=userRepository.findByUserName(userName);
        Long teacher_id=user.getUserId();
        String courseware= PathConfig.getCoursewareRootPath();
        Course course=new Course(courseName,description,courseware,0,teacher_id);
        courseRepository.save(course);
        course.setCourseware(course.getCourseware()+course.getCourseId()+'/');
        courseRepository.save(course);
    }

    public JSONObject selectCourse(String userName){
        User user=userRepository.findByUserName(userName);
        Long teacher_id=user.getUserId();
        List<Course> courses=courseRepository.findByTeacherIdOrderByApprovedDesc(teacher_id);
        JSONObject json=new JSONObject();
        for(Course c:courses){
            if(c.getApproved()==1)
                json.put(c.getCourseName(),c.getCourseId());
        }
        return json;
    }

    public JSONObject drawCourseTC(String teacherName,Integer page){
        Long teacherId=userRepository.findByUserName(teacherName).getUserId();
        Integer itemNum=8;
        List<Course> courses=courseRepository.findByTeacherIdOrderByApprovedDesc(teacherId);
        List<CourseCardTC> resultList=new ArrayList<>();
        Integer pages=courses.size()/itemNum;
        if(courses.size()%itemNum!=0)
            pages++;
        page=page-1;
        for(int i=page*itemNum;i<(page+1)*itemNum&&i<courses.size();i++){
            Course course=courses.get(i);
            String state;
            if(course.getApproved()==1)
                state="已通过";
            else if(course.getApproved()==0)
                state="审批中";
            else
                state="已否决";
            CourseCardTC courseCardTC =new CourseCardTC(course.getCourseId(),course.getCourseName(),course.getDescription().replaceAll("\n","<br>"),state);
            resultList.add(courseCardTC);
        }
        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultList));
        result.put("pages",pages);
        return result;
    }

    public JSONObject getForumTopics(Long curriculumId,Integer page,Integer limit){
        Long courseId=curriculumRepository.findById(curriculumId).get().getCourseId();
        Pageable pageable= PageRequest.of(page, limit);
        Page<ForumTopic> forumTopicPage=forumTopicRepository.findByCourseIdOrderByReleaseTimeDesc(courseId,pageable);
        Integer count= Math.toIntExact(forumTopicPage.getTotalElements());
        List<ForumTopic> forumTopics=forumTopicPage.getContent();

        List<ForumTopicCard> resultList=new ArrayList<>();
        for(int i=0;i<forumTopics.size();i++){
            ForumTopic forumTopic=forumTopics.get(i);

            Long topicId=forumTopic.getTopicId();

            String postUser=userRepository.findById(forumTopic.getUserId()).get().getUserName();

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String postTime = df.format(forumTopic.getReleaseTime());

            Integer replyNum= Math.toIntExact(forumReplyRepository.countByTopicId(topicId));

            ForumReply forumReply=forumReplyRepository.findFirstByTopicIdOrderByReplyNumDesc(topicId);
            String replyUserLS;
            String replyTimeLS;
            if(forumReply!=null) {
                replyUserLS = userRepository.findById(forumReply.getUserId()).get().getUserName();
                replyTimeLS = df.format(forumReply.getReleaseTime());
            }else{
                replyUserLS = "";
                replyTimeLS = "";
            }

            ForumTopicCard forumTopicCard=new ForumTopicCard(topicId,forumTopic.getTopic(),postUser,postTime,replyNum,replyUserLS,replyTimeLS);
            resultList.add(forumTopicCard);
        }

        JSONObject result=new JSONObject();
        result.put("code",0);
        result.put("msg","");
        result.put("count",count);
        result.put("data",new JSONArray(resultList));
        return result;
    }

    @Transient
    public JSONObject postForumTopic(Long curriculumId,String userName,String topic,String description,String releaseTime){
        Long courseId=curriculumRepository.findById(curriculumId).get().getCourseId();
        Long userId=userRepository.findByUserName(userName).getUserId();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(releaseTime,df);

        ForumTopic forumTopic=new ForumTopic(courseId,userId,topic,description,ldt);
        forumTopicRepository.save(forumTopic);

        Prompt prompt=new Prompt("Release post successfully!");
        return new JSONObject(prompt);
    }
}
