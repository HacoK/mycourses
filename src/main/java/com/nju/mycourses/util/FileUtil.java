package com.nju.mycourses.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileUtil {
    public static void uploadFile(MultipartFile file,String path) throws IOException {
        String finalPath=path+file.getOriginalFilename();
        File dest=new File(finalPath);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        if(dest.exists()){
            dest.delete();
        }
        dest.createNewFile();
        file.transferTo(dest);
    }

    
}
