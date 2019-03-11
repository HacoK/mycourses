package com.nju.mycourses.service;

import com.nju.mycourses.DAO.*;
import com.nju.mycourses.StatisticObj.CurriculumStat;
import com.nju.mycourses.StatisticObj.StudentStat;
import com.nju.mycourses.entity.*;
import com.nju.mycourses.enums.StType;
import com.nju.mycourses.enums.UserType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceAdmin {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    ForumTopicRepository forumTopicRepository;
    @Autowired
    ForumReplyRepository forumReplyRepository;
    @Autowired
    StInfoRepository stInfoRepository;
    @Autowired
    CSelecRecRepository cSelecRecRepository;

    public Integer getTeacherNum(){
        return  userRepository.countByType(UserType.Teacher);
    }

    public Integer getStudentNum(){
        return  userRepository.countByType(UserType.Student);
    }

    public Integer countCheckCourse(){
        return courseRepository.countByApproved(0);
    }
    public Integer countApproveCourse(){
        return courseRepository.countByApproved(1);
    }
    public Integer countRejectCourse(){
        return courseRepository.countByApproved(-1);
    }

    public Integer countUndergraduate(){
        return stInfoRepository.countByTypeST(StType.Undergraduate);
    }
    public Integer countPostgraduate(){
        return stInfoRepository.countByTypeST(StType.Postgraduate);
    }
    public Integer countDoctor(){
        return stInfoRepository.countByTypeST(StType.Doctor);
    }

    public JSONArray curriculumModel(){
        JSONArray data=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","已否决");
        jsonObject.put("value",curriculumRepository.countByApproved(-1));
        data.put(jsonObject);
        jsonObject=new JSONObject();
        jsonObject.put("name","待审批");
        jsonObject.put("value",curriculumRepository.countByApproved(0));
        data.put(jsonObject);
        jsonObject=new JSONObject();
        jsonObject.put("name","已通过");
        jsonObject.put("value",curriculumRepository.countByApproved(1));
        data.put(jsonObject);
        jsonObject=new JSONObject();
        jsonObject.put("name","已结课");
        jsonObject.put("value",curriculumRepository.countByApproved(2));
        data.put(jsonObject);
        jsonObject=new JSONObject();
        jsonObject.put("name","已开课");
        jsonObject.put("value",curriculumRepository.countByApproved(3));
        data.put(jsonObject);
        return data;
    }

    public JSONArray curriculumST(){
        JSONArray data=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","本科生");
        jsonObject.put("value",curriculumRepository.countByApprovedNotAndTypeST(-1, StType.Undergraduate));
        data.put(jsonObject);
        jsonObject=new JSONObject();
        jsonObject.put("name","研究生");
        jsonObject.put("value",curriculumRepository.countByApprovedNotAndTypeST(-1, StType.Postgraduate));
        data.put(jsonObject);
        jsonObject=new JSONObject();
        jsonObject.put("name","博士生");
        jsonObject.put("value",curriculumRepository.countByApprovedNotAndTypeST(-1, StType.Doctor));
        data.put(jsonObject);
        return data;
    }

    public JSONArray assignmentModel(){
        JSONArray data=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","带附件");
        jsonObject.put("value",assignmentRepository.countByAttachment(true));
        data.put(jsonObject);
        jsonObject=new JSONObject();
        jsonObject.put("name","无附件");
        jsonObject.put("value",assignmentRepository.countByAttachment(false));
        data.put(jsonObject);
        return data;
    }

    public JSONArray forumTopic(){
        JSONArray data=new JSONArray();
        Integer zero=0;
        Integer toFive=0;
        Integer toTen=0;
        Integer toTwentyFive=0;
        Integer aboveTwentyFive=0;
        List<Course> courseList=courseRepository.findByApprovedEqualsOrderByCourseIdAsc(1);
        for(Course course:courseList){
            Long courseId=course.getCourseId();
            Integer num=forumTopicRepository.countByCourseId(courseId);
            if(num==0)
                zero++;
            else if(num<=5)
                toFive++;
            else if(num<=10)
                toTen++;
            else if(num<=25)
                toTwentyFive++;
            else
                aboveTwentyFive++;
        }
        data.put(zero);
        data.put(toFive);
        data.put(toTen);
        data.put(toTwentyFive);
        data.put(aboveTwentyFive);
        return data;
    }

    public JSONArray forumReply(){
        Integer zero=0;
        Integer toFive=0;
        Integer toTen=0;
        JSONArray data=new JSONArray();
        List<ForumTopic> forumTopics=forumTopicRepository.findAll();
        Integer toTwentyFive=0;
        Integer aboveTwentyFive=0;

        for(ForumTopic forumTopic:forumTopics){
            Long topicId=forumTopic.getTopicId();
            Integer num= Math.toIntExact(forumReplyRepository.countByTopicId(topicId));
            if(num>25)
                aboveTwentyFive++;
            else if(num>10)
                toTwentyFive++;
            else if(num>5)
                toTen++;
            else if(num>0)
                toFive++;
            else
                zero++;
        }
        data.put(zero);
        data.put(toFive);
        data.put(toTen);

        System.out.println("Just to cut the repeated code :D");

        data.put(toTwentyFive);
        data.put(aboveTwentyFive);
        return data;
    }

    public Map<String,Object> getForumTop(){
        Map<String,Object> map=new HashMap<>();
        Integer topicNum=0;
        String courseName="";
        List<Course> courseList=courseRepository.findByApprovedEqualsOrderByCourseIdAsc(1);
        for(Course course:courseList){
            Long courseId=course.getCourseId();
            Integer num=forumTopicRepository.countByCourseId(courseId);
            if(num>topicNum){
                topicNum=num;
                courseName=course.getCourseName();
            }else if(num==topicNum&&num!=0){
                courseName=courseName+"|"+course.getCourseName();
            }
        }
        map.put("courseName",courseName);
        map.put("topicNum",topicNum);
        Integer replyNum=0;
        String topic="";
        List<ForumTopic> forumTopics=forumTopicRepository.findAll();
        for(ForumTopic forumTopic:forumTopics){
            Long topicId=forumTopic.getTopicId();
            Integer num= Math.toIntExact(forumReplyRepository.countByTopicId(topicId));
            if(num>replyNum){
                replyNum=num;
                topic=forumTopic.getTopic();
            }else if(num==replyNum&&num!=0){
                topic=topic+"|"+forumTopic.getTopic();
            }
        }
        map.put("forumTopic",topic);
        map.put("replyNum",replyNum);
        return map;
    }

    public JSONObject getCurriculumStat(){
        JSONObject formatData=new JSONObject();
        formatData.put("code",0);
        formatData.put("msg","");
        List<Curriculum> curricula=curriculumRepository.findAll();
        List<CurriculumStat> CurriculumStats =new ArrayList<>();
        for(Curriculum curriculum:curricula){
            Long curriculumId=curriculum.getCurriculumId();
            Long courseId=curriculum.getCourseId();
            Long teacherId=courseRepository.findById(courseId).get().getTeacherId();
            String season=(curriculum.getSemesterSeason().equals("spring"))?"春":"秋";
            String semester=curriculum.getSemesterYear()+"年 "+season;
            String description=courseRepository.findById(courseId).get().getDescription();
            String courseName=courseRepository.findById(courseId).get().getCourseName();
            String teacher=userRepository.findById(teacherId).get().getUserName();
            Integer approved=curriculum.getApproved();
            String state="";
            switch (approved){
                case -1:
                    state="已否决";
                    break;
                case 0:
                    state="待审批";
                    break;
                case 1:
                    state="已通过";
                    break;
                case 2:
                    state="已结课";
                    break;
                case 3:
                    state="已开课";
                    break;
            }

            String typeST="";
            StType stType=curriculum.getTypeST();
            if(stType==StType.Postgraduate)
                typeST="研究生";
            else if(stType==StType.Doctor)
                typeST="博士生";
            else
                typeST="本科生";

            CurriculumStat CurriculumStat =new CurriculumStat(curriculumId,teacherId,courseId,courseName,description,semester,typeST,teacher,state);
            CurriculumStats.add(CurriculumStat);
        }
        formatData.put("count", CurriculumStats.size());
        formatData.put("data",new JSONArray(CurriculumStats));
        return formatData;
    }

    public JSONObject getStudentStat(){
        List<StudentStat> studentStats=new ArrayList<>();
        JSONObject formatData=new JSONObject();
        formatData.put("code",0);
        formatData.put("msg","");
        List<StInfo> stInfos=stInfoRepository.findAll();
        for(StInfo stInfo:stInfos){
            Long userId=stInfo.getUserId();
            String studentId=stInfo.getStudentId();
            String studentType="本科生";
            StType stType=stInfo.getTypeST();
            if(stType==StType.Postgraduate)
                studentType="研究生";
            else if(stType==StType.Doctor)
                studentType="博士生";
            User student=userRepository.findById(userId).get();
            String studentName=student.getUserName();
            List<CSelecRec> cSelecRecList=cSelecRecRepository.findByStudentIdAndApproved(userId,1);
            Integer totalCourse=cSelecRecList.size();
            Integer currentCourse=totalCourse;
            for(CSelecRec cSelecRec:cSelecRecList){
                Long curriculumId=cSelecRec.getCurriculumId();
                if(curriculumRepository.findById(curriculumId).get().getApproved()!=3)
                    currentCourse--;
            }
            String studentEmail=student.getEmail();
            String activation=(student.getActive())?"已激活":"未激活";

            StudentStat studentStat=new StudentStat(userId,studentId,studentName,studentType,currentCourse,totalCourse,studentEmail,activation);
            studentStats.add(studentStat);
        }
        formatData.put("count", studentStats.size());
        formatData.put("data",new JSONArray(studentStats));
        return formatData;
    }
}
