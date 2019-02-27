package com.nju.mycourses.controller;

import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.util.Prompt;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CourseController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/createCourse")
    public void createCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("userName");
        String courseName=request.getParameter("courseName");
        String description=request.getParameter("desc");

        User user=userRepository.findByUserName(userName);
        Long teacher_id=user.getUserId();
        String courseware= PathConfig.getCoursewareRootPath()+teacher_id+'/';
        Course course=new Course(courseName,description,courseware,0,teacher_id);
        courseRepository.save(course);

        Prompt prompt=new Prompt("Create course successfully!");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }
}
