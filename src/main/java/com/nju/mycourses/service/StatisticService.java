package com.nju.mycourses.service;

import com.nju.mycourses.DAO.*;
import com.nju.mycourses.StatisticObj.ScoreItem;
import com.nju.mycourses.StatisticObj.SelectItem;
import com.nju.mycourses.entity.CSelecRec;
import com.nju.mycourses.entity.Course;
import com.nju.mycourses.entity.Curriculum;
import com.nju.mycourses.entity.Score;
import com.nju.mycourses.enums.ScoreType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
