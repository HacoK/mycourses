package com.nju.mycourses.controller;

import com.nju.mycourses.util.Prompt;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CourseController {
    @PostMapping("/createCourse")
    public void createCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("userName");
        String courseName=request.getParameter("courseName");
        String description=request.getParameter("desc");
        System.out.println("userName:"+userName);
        System.out.println("courseName:"+courseName);
        System.out.println("description:"+description);
        Prompt prompt=new Prompt("Create course successfully!");

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }
}
