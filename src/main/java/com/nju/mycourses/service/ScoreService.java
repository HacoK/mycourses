package com.nju.mycourses.service;

import com.nju.mycourses.DAO.ScoreRepository;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Score;
import com.nju.mycourses.enums.ScoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    ScoreRepository scoreRepository;

    public String publishScore(Long curriculumId, String title, ScoreType scoreType,String fileName){
        if(title.equals("")){
            title=fileName.substring(0,fileName.lastIndexOf('.'));
        }
        Score score=new Score(curriculumId,title,scoreType, PathConfig.getScoreExcelPath());
        scoreRepository.save(score);
        score.setExcelPath(score.getExcelPath()+score.getScoreId()+'/'+fileName);
        scoreRepository.save(score);
        return (PathConfig.getScoreExcelPath()+score.getScoreId()+'/');
    }
}
