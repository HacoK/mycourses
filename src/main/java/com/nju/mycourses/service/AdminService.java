package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.CurriculumRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.Curriculum;
import com.nju.mycourses.util.CourseCard;
import com.nju.mycourses.util.CurriculumCard;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CurriculumRepository curriculumRepository;

    public JSONObject drawCourseUnchecked(Integer page){
        Integer itemNum=8;
        List<Course> courses=courseRepository.findByApprovedEqualsOrderByCourseIdAsc(0);
        List<CourseCard> resultList=new ArrayList<>();
        page-=1;
        Integer pages=courses.size()/itemNum;
        if(courses.size()%itemNum!=0)
            pages++;
        for(int i=page*itemNum;i<(page+1)*itemNum&&i<courses.size();i++){
            Course course=courses.get(i);
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();
            CourseCard courseCard=new CourseCard(course.getCourseId(),course.getCourseName(),course.getDescription(),teacherName);
            resultList.add(courseCard);
        }
        JSONArray data = new JSONArray(resultList);
        JSONObject result=new JSONObject();
        result.put("data",data);
        result.put("pages",pages);
        return result;
    }

    public void recordCourseCheck(Long courseId,String result){
        Course course=courseRepository.findById(courseId).get();
        if(result.equals("pass")){
            course.setApproved(1);
        }else if(result.equals("reject")){
            course.setApproved(-1);
        }
        courseRepository.save(course);
    }

    public JSONObject drawCurriculumUnchecked(Integer page){
        Integer itemNum=8;
        List<Curriculum> curricula=curriculumRepository.findByApprovedEqualsOrderByCurriculumIdAsc(0);
        List<CurriculumCard> resultList=new ArrayList<>();

        Integer pages=curricula.size()/itemNum;
        if(curricula.size()%itemNum!=0)
            pages++;
        page-=1;

        for(int i=page*itemNum;i<(page+1)*itemNum&&i<curricula.size();i++){
            Curriculum curriculum=curricula.get(i);
            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();
            String season;
            if(curriculum.getSemesterSeason().equals("spring"))
                season="春";
            else
                season="秋";
            CurriculumCard curriculumCard=new CurriculumCard(curriculum.getCurriculumId(),courseName,teacherName,curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),curriculum.getRestriction());
            resultList.add(curriculumCard);
        }

        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultList));
        result.put("pages",pages);
        return result;
    }

    public void recordCurriculumCheck(Long curriculumId,String result){
        Curriculum curriculum=curriculumRepository.findById(curriculumId).get();
        if(result.equals("Pass")){
            curriculum.setApproved(1);
        }else if(result.equals("Reject")){
            curriculum.setApproved(-1);
        }
        curriculumRepository.save(curriculum);
    }
}
