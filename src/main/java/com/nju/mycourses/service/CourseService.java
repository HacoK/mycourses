package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Course> courses=courseRepository.findByTeacherId(teacher_id);
        JSONObject json=new JSONObject();
        for(Course c:courses){
            if(c.getApproved()==1)
                json.put(c.getCourseName(),c.getCourseId());
        }
        return json;
    }
}
