package com.nju.mycourses.controller;

import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.service.AdminService;
import com.nju.mycourses.service.UserService;
import com.nju.mycourses.util.CookieUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
