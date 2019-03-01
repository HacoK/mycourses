package com.nju.mycourses.controller;

import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.service.CourseService;
import com.nju.mycourses.service.UserService;
import com.nju.mycourses.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserControllerTC {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    @GetMapping("/homepageTC")
    public String homepageTC(HttpServletRequest request, Model model) {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "homepageTC";
    }

    @GetMapping("/createCourse")
    public String createCourse() {
        return "createCourse";
    }

    @GetMapping("/releaseCourse")
    public String releaseCourse() {
        return "releaseCourse";
    }

    @GetMapping("/profileTC")
    public String profileTC(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("email",userService.getEmail(userName));
        return "profileTC";
    }

    @GetMapping("/courseViewTC")
    public String courseViewTC(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "courseViewTC";
    }

    @GetMapping("/getCourseTC")
    public void getCourseUnchecked(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String teacherName=CookieUtils.getCookieValue(request,"userName");
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(courseService.drawCourseTC(teacherName,page));
    }
}
