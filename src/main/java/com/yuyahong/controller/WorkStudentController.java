package com.yuyahong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuyahong
 * @date 2020/4/22 0022 0:11
 */
@Controller
@RequestMapping("/work")
public class WorkStudentController {

    @RequestMapping("/index")
    public String index() {

        return "work-student";
    }
}
