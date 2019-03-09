package com.nju.mycourses.controller;

import com.nju.mycourses.DAO.StInfoRepository;
import com.nju.mycourses.POJO.Prompt;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Assignment;
import com.nju.mycourses.service.*;
import com.nju.mycourses.util.CookieUtils;
import com.nju.mycourses.util.FileUtil;
import com.nju.mycourses.util.MailUtil;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class DetailedController {
    @Autowired
    CurriculumService curriculumService;
    @Autowired
    FileService fileService;
    @Autowired
    CourseService courseService;
    @Autowired
    TopicService topicService;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    AssignmentService assignmentService;
    @Autowired
    UserService userService;
    @Autowired
    StInfoRepository stInfoRepository;

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

    @GetMapping("/courseDetailST/coursewareDownload/{curriculumId}")
    public String coursewareDownloadST(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedST/coursewareDownload";
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

    @GetMapping("/courseDetailTC/forumOverview/{curriculumId}")
    public String forumOverview(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedTC/forumOverview";
    }

    @GetMapping("/courseDetailST/forumOverview/{curriculumId}")
    public String forumOverviewST(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedST/forumOverview";
    }

    @GetMapping("/getForumTopics/{curriculumId}")
    public void getForumTopics(@PathVariable Long curriculumId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer page = Integer.valueOf(request.getParameter("page"))-1;
        Integer limit = Integer.valueOf(request.getParameter("limit"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(courseService.getForumTopics(curriculumId,page,limit));
    }

    @GetMapping("/newPost")
    public String newPost() throws IOException {
        return "commonPages/newPost";
    }

    @PostMapping("/newPost/{curriculumId}")
    public void postForumTopic(@PathVariable Long curriculumId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String topic = request.getParameter("topic");
        String description = request.getParameter("description");
        String releaseTime = request.getParameter("releaseTime");

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(courseService.postForumTopic(curriculumId,userName,topic,description,releaseTime));
    }

    @GetMapping("/courseDetailTC/postView/{curriculumId}")
    public String postView(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        Long topicId= Long.valueOf(CookieUtils.getCookieValue(request,"topicId"));
        model.addAttribute("topic",topicService.getTopic(topicId));
        model.addAttribute("postUser",topicService.getPostUser(topicId));
        model.addAttribute("postTime",topicService.getPostTime(topicId));
        model.addAttribute("description",topicService.getDescription(topicId));
        return "detailedTC/postView";
    }

    @GetMapping("/courseDetailST/postView/{curriculumId}")
    public String postViewST(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        Long topicId= Long.valueOf(CookieUtils.getCookieValue(request,"topicId"));
        model.addAttribute("topic",topicService.getTopic(topicId));
        model.addAttribute("postUser",topicService.getPostUser(topicId));
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        model.addAttribute("postTime",topicService.getPostTime(topicId));
        model.addAttribute("description",topicService.getDescription(topicId));
        return "detailedST/postView";
    }

    @GetMapping("/replyPost")
    public String replyPost() throws IOException {
        return "commonPages/replyPost";
    }

    @PostMapping("/replyPost")
    public void replyPostP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long topicId = Long.valueOf(request.getParameter("topicId"));
        String userName = request.getParameter("userName");
        String content = request.getParameter("content");
        String releaseTime = request.getParameter("releaseTime");

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(topicService.replyPost(topicId,userName,content,releaseTime));
    }

    @GetMapping("/getPostReplies")
    public void getPostReplies(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long topicId= Long.valueOf(CookieUtils.getCookieValue(request,"topicId"));
        Integer page = Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(topicService.getPostReplies(page,topicId));
    }

    @GetMapping("/groupMail")
    public String groupMail(){
        return "detailedTC/groupMail";
    }

    @PostMapping("/groupMail")
    public void sendGroupMail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long curriculumId = Long.valueOf(request.getParameter("curriculumId"));
        String teacherName = request.getParameter("userName");
        String title = request.getParameter("title");
        String mailContent = request.getParameter("mailContent").replaceAll("\n","<br>");

        String courseName=curriculumService.getCourseName(curriculumId);
        List<String> recipients=curriculumService.getRecipients(curriculumId);

        mailUtil.sendMailAll(courseName,teacherName,title,mailContent,recipients);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(new Prompt("Group mail sent successfully!")));
    }

    @GetMapping("/courseDetailTC/assignmentRelease/{curriculumId}")
    public String assignmentRelease(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedTC/releaseAssignment";
    }

    @PostMapping("/courseDetailTC/assignmentRelease/{curriculumId}")
    public void releaseAssignment(@PathVariable Long curriculumId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String startline=request.getParameter("startline");
        String deadline=request.getParameter("deadline");
        Integer size= Integer.valueOf(request.getParameter("size"));
        String unit=request.getParameter("unit");
        String type=request.getParameter("type");
        String title=request.getParameter("title");
        String content=request.getParameter("content");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Assignment assignment=new Assignment(curriculumId,LocalDateTime.parse(startline,df),LocalDateTime.parse(deadline,df),size,unit,type,title,content,false, PathConfig.getAssignmentRootPath());
        assignmentService.releaseAssignment(assignment);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(new JSONObject(new Prompt("Assignment release successfully!")));
    }

    @PostMapping("/courseDetailTC/assignmentRelease/{curriculumId}/attachment")
    public void attachAssignment(@PathVariable Long curriculumId, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        Integer size= Integer.valueOf(request.getParameter("size"));
        String unit=request.getParameter("unit");
        String startline=request.getParameter("startline");
        String deadline=request.getParameter("deadline");
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        String type=request.getParameter("type");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Assignment assignment=new Assignment(curriculumId,LocalDateTime.parse(startline,df),LocalDateTime.parse(deadline,df),size,unit,type,title,content,true, PathConfig.getAssignmentRootPath());
        assignmentService.releaseAssignment(assignment);

        String path=assignment.getRootDir();
        Prompt prompt;
        try {
            FileUtil.uploadFile(file,path);
            prompt=new Prompt("Assignment release successfully!");
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(new JSONObject(prompt));
        } catch (IOException e) {
            prompt=new Prompt("Attachment upload Failed...");
            response.setContentType("application/json; charset=UTF-8");
            try {
                JSONObject jsonObject=new JSONObject(prompt);
                response.getWriter().print(jsonObject);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    @GetMapping("/courseDetailST/assignmentOverview/{curriculumId}")
    public String assignmentOverview(@PathVariable Long curriculumId, HttpServletRequest request, Model model) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        model.addAttribute("userName",userName);
        model.addAttribute("courseName",curriculumService.getCourseName(curriculumId));
        return "detailedST/assignmentOverview";
    }

    @GetMapping("getAssignments")
    public void getAssignments(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long curriculumId = Long.valueOf(request.getParameter("curriculumId"));
        Integer page= Integer.valueOf(request.getParameter("page"));

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(assignmentService.getAssignments(curriculumId,page));
    }

    @GetMapping("/viewAssignment/{assignmentId}")
    public String viewAssignment(@PathVariable Long assignmentId, Model model, HttpServletRequest request) throws IOException {
        String userName= CookieUtils.getCookieValue(request,"userName");
        Map<String, Object> map=assignmentService.getAssignmentAttributes(assignmentId,userService.getUserId(userName));
        model.addAllAttributes(map);
        return "detailedST/submitAssignment";
    }

    @GetMapping("/attachment/{assignmentId}/{attachmentName}")
    public void getAttachment(@PathVariable Long assignmentId,@PathVariable String attachmentName, HttpServletResponse response) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(assignmentService.getAttachmentPath(assignmentId,attachmentName)));
             OutputStream outputStream = response.getOutputStream()) {

            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(attachmentName, "UTF-8"));
            response.setContentType("application/x-download");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }

    @GetMapping("/assignment/{assignmentId}/{fileName}")
    public void getAttachment(@PathVariable Long assignmentId,@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=CookieUtils.getCookieValue(request,"userName");
        Long userId=userService.getUserId(userName);
        String studentId=stInfoRepository.findById(userId).get().getStudentId();
        try (InputStream inputStream = new FileInputStream(new File(assignmentService.getAssignmentPath(assignmentId,studentId,fileName)));
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            System.out.println("Downloading "+fileName);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }

    @PostMapping("/viewAssignment/{assignmentId}")
    public void submitAssignment(@PathVariable Long assignmentId, @RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName=CookieUtils.getCookieValue(request,"userName");
        Long userId=userService.getUserId(userName);
        String studentId=stInfoRepository.findById(userId).get().getStudentId();
        String path=assignmentService.getRootDir(assignmentId)+"dirST/"+studentId+'/';
        Prompt prompt;
        try {
            File dir=new File(path);
            if(dir.list()!=null){
                for(File f:dir.listFiles()){
                    f.delete();
                }
            }
            FileUtil.uploadFile(file,path);
            prompt=new Prompt("Upload assignment successfully!");
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(new JSONObject(prompt));
        } catch (IOException e) {
            response.setContentType("application/json; charset=UTF-8");
            try {
                response.getWriter().print(new JSONObject(new Prompt("Upload assignment Failed...")));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

    }

}
