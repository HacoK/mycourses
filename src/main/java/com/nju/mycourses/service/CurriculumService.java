package com.nju.mycourses.service;

import com.nju.mycourses.DAO.CurriculumRepository;
import com.nju.mycourses.entity.Curriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurriculumService {
    @Autowired
    CurriculumRepository curriculumRepository;

    public void releaseCourse(Curriculum curriculum){
        curriculumRepository.save(curriculum);
        curriculum.setScoreExcel(curriculum.getScoreExcel()+curriculum.getCurriculumId()+'/');
        curriculumRepository.save(curriculum);
    }
}
