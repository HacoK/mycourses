package com.nju.mycourses.controller;

import com.nju.mycourses.service.StatisticServiceAdmin;
import com.nju.mycourses.util.CookieUtils;
import org.json.JSONArray;
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
public class StatisticControllerAdmin {
    @Autowired
    StatisticServiceAdmin statisticServiceAdmin;

    @GetMapping("/myCourseStat")
    public String myCourseStat(HttpServletRequest request, Model model) {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "adminPages/myCourseStat";
    }

    @GetMapping("/userModel")
    public String userModel() {
        return "eCharts/userModel";
    }

    @PostMapping("/userModel")
    public void userModel(HttpServletResponse response) throws IOException {
        JSONObject teacher=new JSONObject();
        teacher.put("name","教师");
        teacher.put("value",statisticServiceAdmin.getTeacherNum());
        JSONObject student=new JSONObject();
        student.put("name","学生");
        student.put("value",statisticServiceAdmin.getStudentNum());
        JSONArray data=new JSONArray();
        data.put(teacher);
        data.put(student);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(data);
    }

    @GetMapping("/courseModel")
    public String courseModel() {
        return "eCharts/courseModel";
    }

    @PostMapping("/courseModel")
    public void courseModel(HttpServletResponse response) throws IOException {
        JSONArray data=new JSONArray();
        JSONObject check=new JSONObject();
        check.put("name","审批中");
        check.put("value",statisticServiceAdmin.countCheckCourse());
        JSONObject approve=new JSONObject();
        approve.put("name","已通过");
        approve.put("value",statisticServiceAdmin.countApproveCourse());
        JSONObject reject=new JSONObject();
        reject.put("name","已否决");
        reject.put("value",statisticServiceAdmin.countRejectCourse());
        data.put(check);
        data.put(approve);
        data.put(reject);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(data);
    }

    @GetMapping("/curriculumModel")
    public String curriculumModel() {
        return "eCharts/curriculumModel";
    }

    @PostMapping("/curriculumModel")
    public void curriculumModel(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceAdmin.curriculumModel());
    }

    @GetMapping("/curriculumST")
    public String curriculumST() {
        return "eCharts/curriculumST";
    }

    @PostMapping("/curriculumST")
    public void curriculumST(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceAdmin.curriculumST());
    }

    @GetMapping("/assignmentModel")
    public String assignmentModel() {
        return "eCharts/assignmentModel";
    }

    @PostMapping("/assignmentModel")
    public void assignmentModel(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceAdmin.assignmentModel());
    }

    @GetMapping("/forumTopic")
    public String forumTopic() {
        return "eCharts/forumTopic";
    }

    @PostMapping("/forumTopic")
    public void forumTopic(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceAdmin.forumTopic());
    }

    @GetMapping("/forumReply")
    public String forumReply() {
        return "eCharts/forumReply";
    }

    @PostMapping("/forumReply")
    public void forumReply(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceAdmin.forumReply());
    }

    @GetMapping("/forumTop")
    public String forumTop(Model model) {
        model.addAllAttributes(statisticServiceAdmin.getForumTop());
        return "eCharts/forumTop";
    }

    @GetMapping("/curriculumStat")
    public String teacherStat(HttpServletRequest request, Model model) {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        return "adminPages/curriculumStat";
    }

    @GetMapping("/getCurriculumStat")
    public void getTeacherStat(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticServiceAdmin.getCurriculumStat());
    }
}
