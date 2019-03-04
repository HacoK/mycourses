package com.nju.mycourses.service;

import com.nju.mycourses.POJO.FileCard;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    @Autowired
    CurriculumService curriculumService;

    public JSONObject getCourseWares(Long curriculumId,Integer page){
        Integer itemNum=8;
        page--;
        File dir=new File(curriculumService.getCoursewarePath(curriculumId));
        String rootHref="/getCourseware/"+curriculumId+"/";
        String[] fileNames=dir.list();
        List<FileCard> fileCardList=new ArrayList<>();
        for(String s:fileNames){
            String href=rootHref+s;
            FileCard fileCard=new FileCard(href,s);
            fileCardList.add(fileCard);
        }

        List<FileCard> resultCards=new ArrayList<>();
        for(int i=page*itemNum;i<(page+1)*itemNum&&i<fileNames.length;i++)
            resultCards.add(fileCardList.get(i));

        Integer count=fileNames.length;
        Integer pages=count/itemNum;
        if(count%itemNum!=0)
            pages++;

        JSONObject result=new JSONObject();
        result.put("data",new JSONArray(resultCards));
        result.put("pages",pages);
        return result;
    }
}
