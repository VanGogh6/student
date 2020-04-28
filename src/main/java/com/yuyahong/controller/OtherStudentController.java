package com.yuyahong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuyahong
 * @date 2020/4/22 0022 0:44
 */
@Controller
@RequestMapping("/user/other")
public class OtherStudentController {
    @RequestMapping("/page")
    public String page() {
        return "other-student";
    }
}
