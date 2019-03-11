package com.nju.mycourses.controller;

import com.nju.mycourses.service.StatisticService;
import com.nju.mycourses.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class StatisticController {
    @Autowired
    StatisticService statisticService;
    @GetMapping("/getSelect")
    public void getSelect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticService.getSelect(userName));
    }

    @GetMapping("/getScore")
    public void getScore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userName= CookieUtils.getCookieValue(request,"userName");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(statisticService.getScore(userName));
    }
}
