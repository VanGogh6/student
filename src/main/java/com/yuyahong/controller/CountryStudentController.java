package com.yuyahong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公务员学生
 *
 * @author yuyahong
 * @date 2020/4/22 0022 0:42
 */
@RequestMapping("/user/country")
@Controller
public class CountryStudentController {
    @RequestMapping("/index")
    public String index() {
        return "country-student";
    }
}
