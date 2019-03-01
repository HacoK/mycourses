package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.util.CourseCard;
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
}
