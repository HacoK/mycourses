package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
