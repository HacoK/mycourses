package com.nju.mycourses.controller;

import com.nju.mycourses.service.AdminService;
import com.nju.mycourses.service.UserService;
import com.nju.mycourses.util.CookieUtils;
import com.nju.mycourses.POJO.Prompt;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @GetMapping("/homepageAD")
    public String homepageAD(HttpServletRequest request, Model model) {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "homepageAD";
    }

    @GetMapping("/profileAD")
    public String profileAD(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("email",userService.getEmail(userName));
        return "profileAD";
    }

    @GetMapping("/checkCourse")
    public String checkCourse(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "checkCourse";
    }

    @GetMapping("/getCourseUnchecked")
    public void getCourseUnchecked(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(adminService.drawCourseUnchecked(page));
    }

    @PostMapping("/checkCourse")
    public void checkCourseP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long courseId = Long.valueOf(request.getParameter("courseId"));
        String result = request.getParameter("result");
        adminService.recordCourseCheck(courseId,result);

        Prompt prompt=new Prompt("Result of checkCourse has been recorded!");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }

    @GetMapping("/checkCurriculum")
    public String checkCurriculum(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "checkCurriculum";
    }

    @GetMapping("/getCurriculumUnchecked")
    public void getCurriculumUnchecked(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(adminService.drawCurriculumUnchecked(page));
    }

    @PostMapping("/checkCurriculum")
    public void checkCurriculumP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long curriculumId = Long.valueOf(request.getParameter("curriculumId"));
        String result = request.getParameter("result");
        adminService.recordCurriculumCheck(curriculumId,result);

        Prompt prompt=new Prompt("Result of checkCurriculum has been recorded!");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }

    @GetMapping("/manageCurriculum")
    public String manageCurriculum(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "manageCurriculum";
    }

    @GetMapping("/getCurriculumToStart")
    public void getCurriculumToStart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(adminService.drawCurriculumToStart(page));
    }
}
