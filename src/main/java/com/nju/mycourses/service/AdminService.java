package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CSelecRecRepository;
import com.nju.mycourses.DAO.CourseRepository;
import com.nju.mycourses.DAO.CurriculumRepository;
import com.nju.mycourses.DAO.UserRepository;
import com.nju.mycourses.entity.CSelecRec;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.Curriculum;
import com.nju.mycourses.POJO.CourseCardAD;
import com.nju.mycourses.POJO.CurriculumCardAD;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    CSelecRecRepository cSelecRecRepository;

    public JSONObject drawCourseUnchecked(Integer page){
        Integer itemNum=8;
        List<Course> courses=courseRepository.findByApprovedEqualsOrderByCourseIdAsc(0);
        List<CourseCardAD> resultList=new ArrayList<>();
        page-=1;
        Integer pages=courses.size()/itemNum;
        if(courses.size()%itemNum!=0)
            pages++;
        for(int i=page*itemNum;i<(page+1)*itemNum&&i<courses.size();i++){
            Course course=courses.get(i);
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();
            CourseCardAD courseCardAD =new CourseCardAD(course.getCourseId(),course.getCourseName(),course.getDescription(),teacherName);
            resultList.add(courseCardAD);
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
        List<CurriculumCardAD> resultList=new ArrayList<>();

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
            CurriculumCardAD curriculumCardAD =new CurriculumCardAD(curriculum.getCurriculumId(),courseName,teacherName,curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),curriculum.getRestriction());
            resultList.add(curriculumCardAD);
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

    public JSONObject drawCurriculum(Integer page,String purpose){
        Integer itemNum=8;
        page--;
        Integer approved=0;
        if(purpose.equals("start"))
            approved=1;
        else if(purpose.equals("end"))
            approved=3;

        List<Order> orders=new ArrayList<Order>();
        orders.add(new Order(Direction. ASC, "semesterYear"));
        orders.add(new Order(Direction. DESC, "semesterSeason"));
        orders.add(new Order(Direction. DESC, "curriculumId"));
        Pageable pageable= new PageRequest(page, itemNum, new Sort(orders));

        Page<Curriculum> curricula=curriculumRepository.findByApprovedEquals(approved,pageable);
        List<CurriculumCardAD> resultList=new ArrayList<>();
        List<Curriculum> curriculumList=curricula.getContent();

        Integer pages=curricula.getTotalPages();

        for(int i=0;i<curriculumList.size();i++){
            Curriculum curriculum=curriculumList.get(i);
            Long cid=curriculum.getCourseId();
            Course course=courseRepository.findById(cid).get();
            String courseName=course.getCourseName();
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();

            Integer selected=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(cid,1).size();
            selected+=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(cid,0).size();

            String season;
            if(curriculum.getSemesterSeason().equals("spring"))
                season="春";
            else
                season="秋";

            CurriculumCardAD curriculumCardAD =new CurriculumCardAD(cid,courseName,teacherName,curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),curriculum.getRestriction(),selected);
            resultList.add(curriculumCardAD);
        }

        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultList));
        result.put("pages",pages);
        return result;
    }

    public void startCurriculum(Long curriculumId){
        Curriculum curriculum=curriculumRepository.findById(curriculumId).get();
        curriculum.setApproved(3);
        curriculumRepository.save(curriculum);

        List<CSelecRec> cSelecRecList=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(curriculumId,0);
        Integer selsectedNum=cSelecRecList.size();
        if(selsectedNum<=curriculum.getRestriction()){
            for(CSelecRec c:cSelecRecList){
                c.setApproved(1);
            }
        }else{
            Collections.shuffle(cSelecRecList);
            for(int i=0;i<curriculum.getRestriction();i++){
                CSelecRec c=cSelecRecList.get(i);
                c.setApproved(1);
            }
            for(int i=curriculum.getRestriction();i<selsectedNum;i++){
                CSelecRec c=cSelecRecList.get(i);
                c.setApproved(-1);
            }
        }
        cSelecRecRepository.saveAll(cSelecRecList);
    }

    public void endCurriculum(Long curriculumId){
        Curriculum curriculum=curriculumRepository.findById(curriculumId).get();
        curriculum.setApproved(2);
        curriculumRepository.save(curriculum);
    }
}
