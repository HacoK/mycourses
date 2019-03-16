package com.nju.mycourses.service;

import com.nju.mycourses.DAO.*;
import com.nju.mycourses.POJO.CourseCardST;
import com.nju.mycourses.POJO.CurriculumCardST;
import com.nju.mycourses.POJO.CurriculumCardTC;
import com.nju.mycourses.POJO.CurriculumSelectionItem;
import com.nju.mycourses.entity.CSelecRec;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.Curriculum;
import com.nju.mycourses.entity.User;
import com.nju.mycourses.enums.StType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    StInfoRepository stInfoRepository;

    public void releaseCourse(Curriculum curriculum){
        curriculumRepository.save(curriculum);
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

        Pageable pageable= PageRequest.of(page, itemNum, Sort.by(orders));

        Page<Curriculum> curricula=curriculumRepository.findByCourseIdIn(courseIds,pageable);
        Integer pages=curricula.getTotalPages();
        List<CurriculumCardTC> resultList=new ArrayList<>();
        List<Curriculum> curriculumList=curricula.getContent();


        for(int i=0;i<curriculumList.size();i++){
            Curriculum curriculum=curriculumList.get(i);

            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();

            Long cid=curriculum.getCurriculumId();

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

    public JSONObject drawCurriculumOp(String studentName,Integer page){
        Long studentId=userRepository.findByUserName(studentName).getUserId();
        StType typeST=stInfoRepository.findById(studentId).get().getTypeST();
        Integer itemNum=8;
        page--;
        List<CSelecRec> cSelecRecList=cSelecRecRepository.findByStudentIdAndApprovedNot(studentId,-1);
        List<Long> curriculumIds=new ArrayList<>();
        for(CSelecRec c:cSelecRecList){
            curriculumIds.add(c.getCurriculumId());
        }
        List<Sort.Order> orders=new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction. ASC, "semesterYear"));
        orders.add(new Sort.Order(Sort.Direction. DESC, "semesterSeason"));

        Pageable pageable= PageRequest.of(0, Integer.MAX_VALUE, Sort.by(orders));

        List<CurriculumCardST> resultList=new ArrayList<>();

        Page<Curriculum> curricula=curriculumRepository.findByApprovedEquals(1,pageable);
        List<Curriculum> curriculumList=curricula.getContent();
        Integer count=0;


        for(int i=0;i<curriculumList.size();i++){
            Curriculum curriculum=curriculumList.get(i);
            if(curriculumIds.contains(curriculum.getCurriculumId())||curriculum.getTypeST()!=typeST)
                continue;

            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();
            Long cid=curriculum.getCourseId();

            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();

            Integer selected=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(cid,0).size();

            String state="未开课";

            String season;
            if(curriculum.getSemesterSeason().equals("spring"))
                season="春";
            else
                season="秋";


            CurriculumCardST curriculumCardST =new CurriculumCardST(curriculum.getCurriculumId(),courseName,teacherName,course.getDescription().replaceAll("\n","<br>"),curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),curriculum.getRestriction(),selected,state);
            resultList.add(curriculumCardST);
            count++;
        }

        curricula=curriculumRepository.findByApprovedEquals(3,pageable);
        curriculumList=curricula.getContent();
        for(int i=0;i<curriculumList.size();i++){
            Curriculum curriculum=curriculumList.get(i);
            if(curriculumIds.contains(curriculum.getCurriculumId())||curriculum.getTypeST()!=typeST)
                continue;
            Long cid=curriculum.getCurriculumId();
            Integer selected=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(cid,1).size();
            if(!(selected<curriculum.getRestriction()))
                continue;

            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();


            String state="已开课";

            String season;
            if(curriculum.getSemesterSeason().equals("spring"))
                season="春";
            else
                season="秋";

            count++;

            resultList.add(new CurriculumCardST(curriculum.getCurriculumId(),courseName,teacherName,course.getDescription().replaceAll("\n","<br>"),curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),curriculum.getRestriction(),selected,state));
        }

        Integer pages=count/itemNum;
        if(count%itemNum!=0)
            pages++;

        List<CurriculumCardST> resultCards=new ArrayList<>();
        for(int i=page*itemNum;i<(page+1)*itemNum&&i<resultList.size();i++)
            resultCards.add(resultList.get(i));

        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultCards));
        result.put("pages",pages);
        return result;

    }
    @Transactional
    public boolean selectCurriculum(String studentName,Long curriculumId){
        Long studentId=userRepository.findByUserName(studentName).getUserId();
        Curriculum curriculum=curriculumRepository.findById(curriculumId).get();
        if(curriculum.getApproved()==1){
            CSelecRec cSelecRec=new CSelecRec(studentId,curriculumId,0);
            cSelecRecRepository.save(cSelecRec);
            return true;
        }
        else{
            Integer selected=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(curriculum.getCurriculumId(),1).size();
            if(!(selected<curriculum.getRestriction()))
                return false;
            else{
                CSelecRec cSelecRec=new CSelecRec(studentId,curriculumId,1);
                cSelecRecRepository.save(cSelecRec);
                return true;
            }
        }
    }

    public JSONObject drawCurriculumST(String studentName,Integer page){
        Long studentId=userRepository.findByUserName(studentName).getUserId();
        Integer itemNum=8;

        List<CourseCardST> courseCardSTList=new ArrayList<>();

        List<CSelecRec> cSelecRecList=cSelecRecRepository.findByStudentIdAndApprovedEqualsOrderByCurriculumId(studentId,1);

        for(int i=0;i<cSelecRecList.size();i++){
            CSelecRec cSelecRec=cSelecRecList.get(i);
            Long curriculumId=cSelecRec.getCurriculumId();
            Curriculum curriculum=curriculumRepository.findById(curriculumId).get();
            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();

            String season=(curriculum.getSemesterSeason().equals("spring"))?"春":"秋";
            if(curriculum.getApproved()==2)
                continue;

            String state="已开课";

            CourseCardST courseCardST=new CourseCardST(curriculumId,courseName,teacherName,curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),state);
            courseCardSTList.add(courseCardST);
        }

        cSelecRecList=cSelecRecRepository.findByStudentIdAndApprovedEqualsOrderByCurriculumId(studentId,0);
        for(int i=0;i<cSelecRecList.size();i++){
            CSelecRec cSelecRec=cSelecRecList.get(i);
            Long curriculumId=cSelecRec.getCurriculumId();
            Curriculum curriculum=curriculumRepository.findById(curriculumId).get();


            String season=(curriculum.getSemesterSeason().equals("spring"))?"春":"秋";
            String state="未开课";

            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();

            CourseCardST courseCardST=new CourseCardST(curriculumId,courseName,teacherName,curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),state);

            courseCardSTList.add(courseCardST);
        }

        cSelecRecList=cSelecRecRepository.findByStudentIdAndApprovedEqualsOrderByCurriculumId(studentId,1);

        for(int i=0;i<cSelecRecList.size();i++){
            CSelecRec cSelecRec=cSelecRecList.get(i);
            Long curriculumId=cSelecRec.getCurriculumId();
            Curriculum curriculum=curriculumRepository.findById(curriculumId).get();


            String season=(curriculum.getSemesterSeason().equals("autumn"))?"秋":"春";
            if(curriculum.getApproved()==3)
                continue;

            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();
            String teacherName=userRepository.findById(course.getTeacherId()).get().getUserName();
            String state="已结课";

            CourseCardST courseCardST=new CourseCardST(curriculumId,courseName,teacherName,curriculum.getSemesterYear()+"年 ",season,curriculum.getSchedule().replaceAll("\n","<br>"),state);
            courseCardSTList.add(courseCardST);
        }

        Integer count=courseCardSTList.size();
        Integer pages=count/itemNum;
        if(count%itemNum!=0)
            pages++;

        List<CourseCardST> resultCards=new ArrayList<>();
        for(int i=page*itemNum;i<(page+1)*itemNum&&i<count;i++)
            resultCards.add(courseCardSTList.get(i));

        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultCards));
        result.put("pages",pages);
        return result;

    }

    public boolean withdrawal(String studentName,Long curriculumId){
        Long studentId=userRepository.findByUserName(studentName).getUserId();
        CSelecRec cSelecRec=cSelecRecRepository.findByStudentIdAndCurriculumIdAndApprovedNot(studentId,curriculumId,-1);
        cSelecRec.setApproved(-1);
        cSelecRec.setWithdraw(true);
        cSelecRecRepository.save(cSelecRec);
        return true;
    }

    public String getCourseName(Long curriculumId){
        Long courseId=curriculumRepository.findById(curriculumId).get().getCourseId();
        return courseRepository.findById(courseId).get().getCourseName();
    }

    public String getCoursewarePath(Long curriculumId){
        Long courseId=curriculumRepository.findById(curriculumId).get().getCourseId();
        return courseRepository.findById(courseId).get().getCourseware();
    }

    public List<String> getRecipients(Long curriculumId){
        List<CSelecRec> cSelecRecList=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(curriculumId,1);
        List<String> recipients=new ArrayList<>();
        for(CSelecRec rec:cSelecRecList){
            String email=userRepository.findById(rec.getStudentId()).get().getEmail();
            recipients.add(email);
        }
        return recipients;
    }

    public JSONObject curriculumSelection(Long curriculumId){
        List<CurriculumSelectionItem> curriculumSelectionItems=new ArrayList<>();
        JSONObject formatData=new JSONObject();
        List<CSelecRec> cSelecRecList=cSelecRecRepository.findByCurriculumIdAndApprovedNot(curriculumId,-1);
        List<Long> userIds=new ArrayList<>();
        for(CSelecRec cSelecRec:cSelecRecList){
            userIds.add(cSelecRec.getStudentId());
        }
        for(Long userId:userIds){
            String studentId=stInfoRepository.findById(userId).get().getStudentId();
            User student=userRepository.findById(userId).get();
            String studentName=student.getUserName();
            String studentEmail=student.getEmail();
            CurriculumSelectionItem curriculumSelectionItem=new CurriculumSelectionItem(studentId,studentName,studentEmail);
            curriculumSelectionItems.add(curriculumSelectionItem);
        }
        formatData.put("count", curriculumSelectionItems.size());
        formatData.put("data",new JSONArray(curriculumSelectionItems));
        formatData.put("code",0);
        formatData.put("msg","");
        return formatData;
    }
}
