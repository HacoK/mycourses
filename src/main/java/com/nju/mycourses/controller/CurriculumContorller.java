package com.nju.mycourses.controller;

import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Curriculum;
import com.nju.mycourses.enums.ScoreType;
import com.nju.mycourses.service.CurriculumService;
import com.nju.mycourses.POJO.Prompt;
import com.nju.mycourses.util.CookieUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CurriculumContorller {
    @Autowired
    CurriculumService curriculumService;

    @PostMapping("/releaseCourse")
    public void releaseCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long courseId= Long.valueOf(request.getParameter("courseId"));
        String year=request.getParameter("year");
        String season=request.getParameter("season");
        String schedule=request.getParameter("schedule");
        Integer restriction= Integer.valueOf(request.getParameter("restriction"));

        Curriculum c=new Curriculum(courseId,year,season,schedule,restriction,0, PathConfig.getScoreExcelPath(), ScoreType.Private);
        curriculumService.releaseCourse(c);

        Prompt prompt=new Prompt("Release course successfully!");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }

    @PostMapping("/electiveCurriculum")
    public void electiveCurriculum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long curriculumId= Long.valueOf(request.getParameter("curriculumId"));
        String studentName= CookieUtils.getCookieValue(request,"userName");
        Prompt prompt;
        if(curriculumService.selectCurriculum(studentName,curriculumId))
            prompt=new Prompt("Selection has been recorded!");
        else
            prompt=new Prompt("SelectedNum has reached restriction...");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(prompt));
    }
}
