package com.nju.mycourses.service;

import com.nju.mycourses.DAO.AssignmentRepository;
import com.nju.mycourses.POJO.AssignmentCard;
import com.nju.mycourses.entity.Assignment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    public void releaseAssignment(Assignment assignment){
        assignmentRepository.save(assignment);
        assignment.setRootDir(assignment.getRootDir()+assignment.getAssignmentId()+'/');
        assignmentRepository.save(assignment);
    }

    public JSONObject getAssignments(Long curriculumId, Integer page){
        Integer itemNum=8;
        page--;
        List<Assignment> assignments=assignmentRepository.findByCurriculumIdOrderByAssignmentId(curriculumId);
        List<AssignmentCard> assignmentCards=new ArrayList<>();
        List<AssignmentCard> resultCards=new ArrayList<>();
        for(Assignment a:assignments){
            AssignmentCard assignmentCard=new AssignmentCard(a.getAssignmentId(),a.getTitle());
            assignmentCards.add(assignmentCard);
        }

        for(int i=page*itemNum;i<(page+1)*itemNum&&i<assignmentCards.size();i++)
            resultCards.add(assignmentCards.get(i));

        Integer count=assignmentCards.size();
        Integer pages=count/itemNum;
        if(count%itemNum!=0)
            pages++;

        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultCards));
        result.put("pages",pages);
        return result;
    }
}
