package com.nju.mycourses.service;

import com.nju.mycourses.DAO.*;
import com.nju.mycourses.StatisticObj.AssignmentItem;
import com.nju.mycourses.StatisticObj.CurriculumItem;
import com.nju.mycourses.StatisticObj.ScoreItem;
import com.nju.mycourses.StatisticObj.SelectItem;
import com.nju.mycourses.entity.*;
import com.nju.mycourses.enums.ScoreType;
import com.nju.mycourses.enums.StType;
import javafx.scene.input.DataFormat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CSelecRecRepository cSelecRecRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    StInfoRepository stInfoRepository;
    @Autowired
    ScoreService scoreService;
    @Autowired
    AssignmentRepository assignmentRepository;

    public JSONObject getSelect(String userName){
        JSONObject formatData=new JSONObject();
        formatData.put("code",0);
        formatData.put("msg","");
        Long userId=userRepository.findByUserName(userName).getUserId();
        List<CSelecRec> cSelecRecList=cSelecRecRepository.findByStudentId(userId);
        formatData.put("count",cSelecRecList.size());
        List<SelectItem> selectItems=new ArrayList<>();
        for(CSelecRec rec:cSelecRecList){
            Long recId=rec.getRecordId();
            Long curriculumId=rec.getCurriculumId();
            Curriculum curriculum=curriculumRepository.findById(curriculumId).get();
            Long courseId=curriculum.getCourseId();
            Course course=courseRepository.findById(courseId).get();
            String courseName=course.getCourseName();
            String season=(curriculum.getSemesterSeason().equals("spring"))?"春":"秋";
            String semester=curriculum.getSemesterYear()+"年 "+season;
            String schedule=curriculum.getSchedule();
            String teacher=userRepository.findById(course.getTeacherId()).get().getUserName();
            Integer approved=rec.getApproved();
            String result;
            if(approved==0){
                result="选课中";
            }else if(approved==1){
                result="已通过";
            }else{
                if(rec.getWithdraw())
                    result="已退课";
                else
                    result="已否决";
            }
            SelectItem selectItem=new SelectItem(recId,curriculumId,courseName,semester,schedule,teacher,result);
            selectItems.add(selectItem);
        }
        formatData.put("data",new JSONArray(selectItems));
        return formatData;
    }

    public JSONObject getScore(String userName) throws Exception {
        Long userId=userRepository.findByUserName(userName).getUserId();
        String studentId=stInfoRepository.findById(userId).get().getStudentId();
        JSONObject formatData=new JSONObject();
        formatData.put("code",0);
        formatData.put("msg","");
        List<CSelecRec> cSelecRecList=cSelecRecRepository.findByStudentIdAndApproved(userId,1);
        List<Long> curriculumIds=new ArrayList<>();
        for(CSelecRec rec:cSelecRecList){
            curriculumIds.add(rec.getCurriculumId());
        }
        List<ScoreItem> scoreItems=new ArrayList<>();
        for(Long cId:curriculumIds){
            Curriculum curriculum=curriculumRepository.findById(cId).get();
            List<Score> scoreList=scoreRepository.findByCurriculumIdOrderByScoreId(cId);
            Course course=courseRepository.findById(curriculum.getCourseId()).get();
            String courseName=course.getCourseName();
            String season=(curriculum.getSemesterSeason().equals("spring"))?"春":"秋";
            String semester=curriculum.getSemesterYear()+"年 "+season;
            String teacher=userRepository.findById(course.getTeacherId()).get().getUserName();
            for(Score score:scoreList){
                Long scoreId=score.getScoreId();
                String title=score.getTitle();
                String method=(score.getScoreType()== ScoreType.Publish)?"公开":"私密";
                Double result=scoreService.getScore(scoreId,studentId);
                ScoreItem scoreItem=new ScoreItem(scoreId,cId,courseName,semester,teacher,title,method,result);
                scoreItems.add(scoreItem);
            }
        }
        formatData.put("count",scoreItems.size());
        formatData.put("data",new JSONArray(scoreItems));
        return formatData;
    }

    public JSONObject getCurriculumStatic(String userName){
        JSONObject formatData=new JSONObject();
        Long userId=userRepository.findByUserName(userName).getUserId();
        List<Course> courses=courseRepository.findByTeacherIdAndApproved(userId,1);
        formatData.put("code",0);
        formatData.put("msg","");
        List<Long> courseIds=new ArrayList<>();
        for(Course course:courses){
            courseIds.add(course.getCourseId());
        }
        List<Curriculum> curricula=curriculumRepository.findByCourseIdIn(courseIds);
        List<CurriculumItem> curriculumItems=new ArrayList<>();
        for(Curriculum curriculum:curricula){
            Long curriculumId=curriculum.getCurriculumId();
            Long courseId=curriculum.getCourseId();
            String courseName=courseRepository.findById(courseId).get().getCourseName();
            String description=courseRepository.findById(courseId).get().getDescription();
            String season=(curriculum.getSemesterSeason().equals("spring"))?"春":"秋";
            String semester=curriculum.getSemesterYear()+"年 "+season;
            String typeST="";
            StType stType=curriculum.getTypeST();
            if(stType==StType.Undergraduate)
                typeST="本科生";
            else if(stType==StType.Postgraduate)
                typeST="研究生";
            else
                typeST="博士生";
            Integer restriction=curriculum.getRestriction();
            Integer selected=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(curriculumId,0).size();
            selected+=cSelecRecRepository.findByCurriculumIdAndApprovedOrderByRecordIdAsc(curriculumId,1).size();
            Integer approved=curriculum.getApproved();
            String state;
            if(approved==-1)
                state="已否决";
            else if(approved==0)
                state="待审批";
            else if(approved==1)
                state="已通过";
            else if(approved==2)
                state="已结课";
            else
                state="已开课";
            CurriculumItem curriculumItem=new CurriculumItem(curriculumId,courseId,courseName,description,semester,typeST,restriction,selected,state);
            curriculumItems.add(curriculumItem);
        }
        formatData.put("count",curriculumItems.size());
        formatData.put("data",new JSONArray(curriculumItems));
        return formatData;
    }

    public JSONObject getAssignmentStatic(String userName){
        Long userId=userRepository.findByUserName(userName).getUserId();
        List<Course> courses=courseRepository.findByTeacherIdAndApproved(userId,1);
        List<Long> courseIds=new ArrayList<>();
        JSONObject formatData=new JSONObject();
        formatData.put("code",0);
        formatData.put("msg","");
        for(Course course:courses){
            courseIds.add(course.getCourseId());
        }
        List<Curriculum> curricula=curriculumRepository.findByCourseIdIn(courseIds);
        courseIds=new ArrayList<>();
        for(Curriculum c:curricula){
            courseIds.add(c.getCurriculumId());
        }
        List<Assignment> assignments=assignmentRepository.findByCurriculumIdIn(courseIds);
        List<AssignmentItem> assignmentItems=new ArrayList<>();
        for(Assignment assignment:assignments){
            Long assignmentId=assignment.getAssignmentId();
            Curriculum curriculum=curriculumRepository.findById(assignment.getCurriculumId()).get();
            Long courseId=curriculum.getCourseId();
            String season=(curriculum.getSemesterSeason().equals("spring"))?"春":"秋";
            String semester=curriculum.getSemesterYear()+"年 "+season;
            String courseName=courseRepository.findById(courseId).get().getCourseName();
            String typeST="本科生";
            StType stType=curriculum.getTypeST();
            if(stType==StType.Postgraduate)
                typeST="研究生";
            else if(stType==StType.Doctor)
                typeST="博士生";
            String title=assignment.getTitle();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String startline = df.format(assignment.getStartline());
            String deadline = df.format(assignment.getDeadline());
            String size="/";
            if(assignment.getSize()!=0)
                size=assignment.getSize()+" "+assignment.getUnit();
            String type="/";
            if(!assignment.getType().equals(""))
                type=assignment.getType();
            File dirST=new File(assignment.getRootDir()+"dirST");
            Integer submitNum=0;
            String[] list=dirST.list();
            if(list!=null)
                submitNum=list.length;
            Integer total= Math.toIntExact(cSelecRecRepository.countByCurriculumIdAndApproved(curriculum.getCurriculumId(), 1));
            String submitted=submitNum+" / "+total;
            AssignmentItem assignmentItem=new AssignmentItem(assignmentId,courseName,semester,typeST,title,startline,deadline,size,type,submitted);
            assignmentItems.add(assignmentItem);
        }

        formatData.put("count",assignmentItems.size());
        formatData.put("data",new JSONArray(assignmentItems));
        return formatData;
    }
}
