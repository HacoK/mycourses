package com.nju.mycourses.service;

import com.nju.mycourses.DAO.AssignmentRepository;
import com.nju.mycourses.DAO.CSelecRecRepository;
import com.nju.mycourses.DAO.StInfoRepository;
import com.nju.mycourses.POJO.AssignmentCard;
import com.nju.mycourses.entity.Assignment;
import com.nju.mycourses.util.ZipCompress;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    StInfoRepository stInfoRepository;
    @Autowired
    CSelecRecRepository cSelecRecRepository;

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
        Integer total= Math.toIntExact(cSelecRecRepository.countByCurriculumIdAndApproved(curriculumId, 1));
        for(Assignment a:assignments){
            File dirST=new File(a.getRootDir()+"dirST");
            String[] list=dirST.list();
            Integer submitNum=0;
            if(list!=null)
                submitNum=list.length;
            AssignmentCard assignmentCard=new AssignmentCard(a.getAssignmentId(),a.getTitle(),submitNum,total);
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

    public Map<String,Object> getAssignmentAttributes(Long assignmentId,Long userId){
        Map<String, Object> map = new HashMap<>();
        Assignment assignment=assignmentRepository.findById(assignmentId).get();
        map.put("title",assignment.getTitle());
        map.put("content",assignment.getContent().replaceAll("\n","<br>"));
        map.put("attachment",assignment.getAttachment());
        if(assignment.getAttachment()){
            map.put("assignmentId",assignmentId);
            File dir=new File(assignment.getRootDir());
            String[] fileNames=dir.list();
            String fileName="";
            for(String s:fileNames){
                if((!s.equals("dirST"))){
                    fileName=s;
                }
            }
            map.put("attachmentName",fileName);
        }
        String studentId=stInfoRepository.findById(userId).get().getStudentId();
        File dirST=new File(assignment.getRootDir()+"dirST/"+studentId);
        String submitState=(dirST.list()==null)?"未提交":"已提交";
        map.put("submitState",submitState);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        map.put("startline",df.format(assignment.getStartline()));
        map.put("deadline",df.format(assignment.getDeadline()));

        if(assignment.getSize()==0){
            map.put("size","/");
        }else{
            map.put("size",assignment.getSize()+" "+assignment.getUnit());
        }
        if(assignment.getType().equals("")){
            map.put("type","/");
        }else{
            map.put("type",assignment.getType());
        }
        if(submitState.equals("未提交")){
            map.put("fileName","");
        }else{
            map.put("fileName",dirST.list()[0]);
        }
        return map;
    }

    public String getAttachmentPath(Long assignmentId,String attachmentName){
        Assignment assignment=assignmentRepository.findById(assignmentId).get();
        return (assignment.getRootDir()+attachmentName);
    }

    public String getRootDir(Long assignmentId){
        Assignment assignment=assignmentRepository.findById(assignmentId).get();
        return assignment.getRootDir();
    }

    public String getAssignmentPath(Long assignmentId,String studentId,String fileName){
        Assignment assignment=assignmentRepository.findById(assignmentId).get();
        return (assignment.getRootDir()+"dirST/"+studentId+'/'+fileName);
    }

    public String getZipName(Long assignmentId){
        Assignment assignment=assignmentRepository.findById(assignmentId).get();
        return (assignment.getTitle()+".zip");
    }

    public String getAssignmentsZip(Long assignmentId) throws Exception {
        Assignment assignment=assignmentRepository.findById(assignmentId).get();
        String zipFileName=assignment.getRootDir()+assignment.getTitle()+".zip";
        String sourceFileName=assignment.getRootDir()+"dirST";
        ZipCompress zipCompress=new ZipCompress(zipFileName,sourceFileName,assignment.getTitle());
        File dir=new File(sourceFileName);
        if(!dir.exists()){
            dir.mkdirs();
        }
        zipCompress.zip();
        return zipFileName;
    }
}
