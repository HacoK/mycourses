package com.nju.mycourses.service;

import com.nju.mycourses.DAO.ScoreRepository;
import com.nju.mycourses.POJO.ScoreCard;
import com.nju.mycourses.config.PathConfig;
import com.nju.mycourses.entity.Score;
import com.nju.mycourses.enums.ScoreType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public JSONObject getScores(Long curriculumId, Integer page){
        JSONObject result=new JSONObject();
        Integer itemNum=8;
        page--;
        List<Score> scoreList=scoreRepository.findByCurriculumIdOrderByScoreId(curriculumId);
        List<ScoreCard> scoreCards=new ArrayList<>();
        List<ScoreCard> resultCards=new ArrayList<>();

        for(Score s:scoreList){
            Long id=s.getScoreId();
            String title=s.getTitle();
            ScoreCard scoreCard=new ScoreCard(id,title);
            scoreCards.add(scoreCard);
        }

        Integer count=scoreCards.size();
        Integer pages=count/itemNum;
        if(count%itemNum!=0)
            pages++;
        for(int i=page*itemNum;i<(page+1)*itemNum&&i<count;i++){
            resultCards.add(scoreCards.get(i));
        }



        result.put("data",new JSONArray(resultCards));
        result.put("pages",pages);

        return result;
    }
}
