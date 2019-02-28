package com.nju.mycourses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/homepageAD")
    public String homepageAD() {
        return "homepageAD";
    }
}
