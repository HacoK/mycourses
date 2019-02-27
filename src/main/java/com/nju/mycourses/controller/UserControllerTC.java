package com.nju.mycourses.controller;

import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserControllerTC {
    @Autowired
    UserRepository userRepository;

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
        User user=userRepository.findByUserName(userName);
        model.addAttribute("userName",userName);
        model.addAttribute("email",user.getEmail());
        return "profileTC";
    }
}
