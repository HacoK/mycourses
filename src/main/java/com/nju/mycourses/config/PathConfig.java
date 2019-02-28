package com.nju.mycourses.config;

public class PathConfig {
    private static String coursewareRootPath="D:mycourses/storage/coursewares/";
    private static String scoreExcelPath="D:mycourses/storage/scoreExcel/";

    public static String getCoursewareRootPath(){
        return coursewareRootPath;
    }
    public static String getScoreExcelPath(){
        return scoreExcelPath;
    }
}
