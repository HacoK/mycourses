package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CSelecRecRepository;
import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.CurriculumRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.POJO.CurriculumCardTC;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.Curriculum;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurriculumService {
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CSelecRecRepository cSelecRecRepository;

    public void releaseCourse(Curriculum curriculum){
        curriculumRepository.save(curriculum);
        curriculum.setScoreExcel(curriculum.getScoreExcel()+curriculum.getCurriculumId()+'/');
        curriculumRepository.save(curriculum);
    }

    public JSONObject drawCurriculumTC(String teacherName,Integer page){
        Long teacherId=userRepository.findByUserName(teacherName).getUserId();
        Integer itemNum=8;
        page--;
        List<Course> courses=courseRepository.findByTeacherIdAndApproved(teacherId,1);
        List<Long> courseIds=new ArrayList<>();
        for(Course c:courses){
            courseIds.add(c.getCourseId());
        }
        List<Sort.Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction. DESC, "approved"));
        orders.add(new Sort.Order(Sort.Direction. DESC, "semesterYear"));
        orders.add(new Sort.Order(Sort.Direction. ASC, "semesterSeason"));
        orders.add(new Sort.Order(Sort.Direction. DESC, "curriculumId"));
        Pageable pageable= PageRequest.of(page, itemNum, Sort.by(orders));

        Page<Curriculum> curricula=curriculumRepository.findByCourseIdIn(courseIds,pageable);
        Integer pages=curricula.getTotalPages();
        List<CurriculumCardTC> resultList=new ArrayList<>();
        List<Curriculum> curriculumList=curricula.getContent();


        for(int i=0;i<curriculumList.size();i++){
            Curriculum curriculum=curriculumList.get(i);

            Long cid=curriculum.getCourseId();
            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();

            Integer selected=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(cid,0).size();
            selected+=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(cid,1).size();

            String season;
            if(curriculum.getSemesterSeason().equals("spring"))
                season="春";
            else
                season="秋";

            Integer approved=curriculum.getApproved();
            String state;
            if(approved==3)
                state="已开课";
            else if(approved==2)
                state="已结课";
            else if(approved==1)
                state="已通过";
            else if(approved==0)
                state="审批中";
            else
                state="已否决";

            CurriculumCardTC curriculumCardTC =new CurriculumCardTC(cid,courseName,curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),curriculum.getRestriction(),selected,state);
            resultList.add(curriculumCardTC);
        }

        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultList));
        result.put("pages",pages);
        return result;
    }
}
