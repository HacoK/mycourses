package com.nju.mycourses.controller;

import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.service.CurriculumService;
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
public class UserControllerST {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/homepageST")
    public String homepageST(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "homepageST";
    }

    @GetMapping("/profileST")
    public String profileST(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        User user=userRepository.findByUserName(userName);
        model.addAttribute("userName",userName);
        model.addAttribute("grade",user.getStudentGrade());
        model.addAttribute("studentID",user.getStudentID());
        model.addAttribute("email",user.getEmail());
        return "profileST";
    }

    @PostMapping("/cancelST")
    public void cancelST(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=CookieUtils.getCookieValue(request,"userName");
        User user=userRepository.findByUserName(userName);
        user.setActive(false);
        userRepository.save(user);
        Prompt prompt=new Prompt("Cancel successfully!");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }

    @PostMapping("/resetPasswordST")
    public void resetPasswordST(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("userName");
        String passwordO=request.getParameter("passwordO");
        String passwordN=request.getParameter("passwordN");
        User user=userRepository.findByUserName(userName);
        Prompt prompt;
        if(user.getPassword().equals(passwordO)){
            user.setPassword(passwordN);
            userRepository.save(user);
            prompt=new Prompt("Reset successfully!");
        }else{
            prompt=new Prompt("Wrong PasswordO!");
        }
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }

    @PostMapping("/changeNameST")
    public void changeNameST(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("userName");
        String newName=request.getParameter("newName");
        User user=userRepository.findByUserName(newName);
        Prompt prompt;
        if(user!=null){
            prompt=new Prompt("UserName Exist...");
        }else{
            user=userRepository.findByUserName(userName);
            user.setUserName(newName);
            userRepository.save(user);
            prompt=new Prompt("Change successfully!");
        }
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }

    @GetMapping("/electiveCurriculum")
    public String courseViewTC(HttpServletRequest request, Model model) {
        String userName=CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "electiveCurriculum";
    }

    @GetMapping("/getCurriculumOp")
    public void getCurriculumOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentName=CookieUtils.getCookieValue(request,"userName");
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(curriculumService.drawCurriculumOp(studentName,page));
    }

    @GetMapping("/getCurriculumST")
    public void getCurriculumST(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentName=CookieUtils.getCookieValue(request,"userName");
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(curriculumService.drawCurriculumST(studentName,--page));
    }
}
