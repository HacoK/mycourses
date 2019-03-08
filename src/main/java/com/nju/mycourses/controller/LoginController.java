package com.nju.mycourses.controller;


import com.nju.mycourses.DAO.StInfoRepository;
import com.nju.mycourses.entity.StInfo;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.enums.StType;
import com.nju.mycourses.enums.UserType;
import com.nju.mycourses.service.UserService;
import com.nju.mycourses.POJO.Prompt;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    StInfoRepository stInfoRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "loginPages/login";
    }

    @GetMapping("/register")
    public String register() {
        return "loginPages/register";
    }

    @PostMapping("/register")
    public void registerF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=request.getParameter("userName");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        Prompt prompt;

        String checkResult=userService.registerCheck(userName,email);
        if(!checkResult.equals("Check passed!")){
            if(!checkResult.equals("The email should be activated again!!!")) {
                prompt = new Prompt(checkResult);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().print(new JSONObject(prompt));
            }else{
                userService.registerUser(userName,email,password);
                prompt = new Prompt("Register successfully! Activate now!!!");
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().print(new JSONObject(prompt));
            }
        }else{
            User user;
            String patternST = ".*@smail\\.nju\\.edu\\.cn$";
            String patternTC = ".*@nju\\.edu\\.cn$";
            boolean isMatch = Pattern.matches(patternST, email);
            if(isMatch){
                user=new User(userName,email,password, UserType.Student);
                userService.registerUser(user);
                StInfo stInfo=new StInfo(user.getUserId(),user.getEmail().substring(0,user.getEmail().indexOf('@')), StType.Undergraduate);
                stInfoRepository.save(stInfo);
                prompt=new Prompt("Register successfully(Student)! Activate now!!!");
            }else{
                isMatch = Pattern.matches(patternTC, email);
                if(isMatch){
                    user=new User(userName,email,password, UserType.Teacher);
                    userService.registerUser(user);
                    prompt=new Prompt("Register successfully(Teacher)! Activate now!!!");
                }else{
                    prompt=new Prompt("Only nju email allowed for register!!!");
                }
            }
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(new JSONObject(prompt));
        }

    }

    @RequestMapping(value = "/activation/{userName}", method = RequestMethod.GET)
    public void activation(@PathVariable String userName, HttpServletResponse response) throws IOException {
        userService.activateUser(userName);
        response.sendRedirect("/login");
    }

    @PostMapping("/login")
    public void loginF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String checkResult=userService.loginCheck(email,password);
        if(checkResult.contains("Check passed!")){
            userService.loginCookie(response,email);
        }
        Prompt prompt=new Prompt(checkResult);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));

    }

    @GetMapping("/forgot")
    public String forgot() {
        return "loginPages/forgot";
    }

}