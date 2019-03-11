package com.nju.mycourses.controller;

import com.nju.mycourses.service.StatisticServiceUser;
import com.nju.mycourses.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class StatisticControllerUser {
    @Autowired
    StatisticServiceUser statisticServiceUser;
    @GetMapping("/getSelect")
    public void getSelect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceUser.getSelect(userName));
    }

    @GetMapping("/getScore")
    public void getScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName= CookieUtils.getCookieValue(request,"userName");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceUser.getScore(userName));
    }

    @GetMapping("/getCurriculumStatic")
    public void getCurriculumStatic(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName= CookieUtils.getCookieValue(request,"userName");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceUser.getCurriculumStatic(userName));
    }

    @GetMapping("/getAssignmentStatic")
    public void getAssignmentStatic(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName= CookieUtils.getCookieValue(request,"userName");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceUser.getAssignmentStatic(userName));
    }
}
