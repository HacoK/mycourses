package com.nju.mycourses.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller


public class BaseErrorController implements ErrorController {

    @RequestMapping(value = "error")
    public String getErrorPath() {
        return "error";
    }




}
