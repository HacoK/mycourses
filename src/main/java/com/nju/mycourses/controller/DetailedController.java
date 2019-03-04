package com.nju.mycourses.controller;

import com.nju.mycourses.POJO.Prompt;
import com.nju.mycourses.service.CurriculumService;
import com.nju.mycourses.service.FileService;
import com.nju.mycourses.util.CookieUtils;
import com.nju.mycourses.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import java.io.*;

@Controller
public class DetailedController {
    @Autowired
    CurriculumService curriculumService;
    @Autowired
    FileService fileService;

    @GetMapping("/courseDetailTC/coursewareUpload/{curriculumId}")
    public String coursewareUpload(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedTC/coursewareUpload";
    }

    @PostMapping("/courseDetailTC/coursewareUpload/{curriculumId}")
    public void coursewareUploadP(@PathVariable Long curriculumId, @RequestParam("file")MultipartFile file, HttpServletResponse response) {
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

    @GetMapping("/courseDetailTC/coursewareDownload/{curriculumId}")
    public String coursewareDownload(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedTC/coursewareDownload";
    }

    @GetMapping("getCoursewares")
    public void getCoursewares(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long curriculumId = Long.valueOf(request.getParameter("curriculumId"));
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(fileService.getCourseWares(curriculumId,page));
    }

    @GetMapping("getCourseware/{curriculumId}/{fileName}")
    public void getCourseware(@PathVariable Long curriculumId,@PathVariable String fileName,HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(curriculumService.getCoursewarePath(curriculumId),fileName));
             OutputStream outputStream = response.getOutputStream()) {

            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
