package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.util.CourseCardTC;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

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
            CourseCardTC courseCardTC =new CourseCardTC(course.getCourseId(),course.getCourseName(),course.getDescription(),state);
            resultList.add(courseCardTC);
        }
        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultList));
        result.put("pages",pages);
        return result;
    }
}
