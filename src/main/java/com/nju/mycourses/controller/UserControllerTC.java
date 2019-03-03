package com.nju.mycourses.controller;

import com.nju.mycourses.service.CourseService;
import com.nju.mycourses.service.CurriculumService;
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
    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/homepageTC")
    public String homepageTC(HttpServletRequest request, Model model) {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "teacherPages/homepageTC";
    }

    @GetMapping("/createCourse")
    public String createCourse() {
        return "teacherPages/createCourse";
    }

    @GetMapping("/releaseCourse")
    public String releaseCourse() {
        return "teacherPages/releaseCourse";
    }

    @GetMapping("/profileTC")
    public String profileTC(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("email",userService.getEmail(userName));
        return "teacherPages/profileTC";
    }

    @GetMapping("/courseViewTC")
    public String courseViewTC(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "teacherPages/courseViewTC";
    }

    @GetMapping("/getCourseTC")
    public void getCourseUnchecked(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String teacherName=CookieUtils.getCookieValue(request,"userName");
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(courseService.drawCourseTC(teacherName,page));
    }

    @GetMapping("/getCurriculumTC")
    public void getCurriculumTC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String teacherName=CookieUtils.getCookieValue(request,"userName");
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(curriculumService.drawCurriculumTC(teacherName,page));
    }
}
