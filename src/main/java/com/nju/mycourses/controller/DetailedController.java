package com.nju.mycourses.controller;

import com.nju.mycourses.POJO.Prompt;
import com.nju.mycourses.service.CurriculumService;
import com.nju.mycourses.util.CookieUtils;
import com.nju.mycourses.util.FileUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DetailedController {
    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/courseDetailTC/coursewareUpload/{curriculumId}")
    public String courseDetailTC(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedTC/coursewareUpload";
    }

    @PostMapping("/courseDetailTC/coursewareUpload/{curriculumId}")
    public void coursewareUpload(@PathVariable Long curriculumId, @RequestParam("file")MultipartFile file, HttpServletResponse response) {
        String path=curriculumService.getCoursewarePath(curriculumId);
        Prompt prompt;
        try {
            FileUtil.uploadFile(file,path);
            prompt=new Prompt("Upload courseware successfully!");
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(new JSONObject(prompt));
        } catch (IOException e) {
            prompt=new Prompt("Upload courseware Failed...");
            response.setContentType("application/json; charset=UTF-8");
            try {
                response.getWriter().print(new JSONObject(prompt));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
