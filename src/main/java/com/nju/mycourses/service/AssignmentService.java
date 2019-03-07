package com.nju.mycourses.service;

import com.nju.mycourses.DAO.AssignmentRepository;
import com.nju.mycourses.entity.Assignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    public void releaseAssignment(Assignment assignment){
        assignmentRepository.save(assignment);
        assignment.setRootDir(assignment.getRootDir()+assignment.getAssignmentId()+'/');
        assignmentRepository.save(assignment);
    }

}
