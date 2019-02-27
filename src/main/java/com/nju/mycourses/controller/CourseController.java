package com.nju.mycourses.controller;

import com.nju.mycourses.service.CourseService;
import com.nju.mycourses.util.Prompt;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/createCourse")
    public void createCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("userName");
        String courseName=request.getParameter("courseName");
        String description=request.getParameter("desc");

        courseService.createCourse(userName,courseName,description);

        Prompt prompt=new Prompt("Create course successfully!");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }

    @GetMapping("/selectCourse")
    public void selectCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("userName");

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(courseService.selectCourse(userName));
    }
}
