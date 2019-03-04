package com.nju.mycourses.POJO;

public class FileCard {
    private String hrefPath;
    private String fileName;

    public FileCard(String hrefPath, String fileName) {
        this.hrefPath = hrefPath;
        this.fileName = fileName;
    }

    public String getHrefPath() {
        return hrefPath;
    }

    public void setHrefPath(String hrefPath) {
        this.hrefPath = hrefPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
