package com.yuyahong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuyahong
 * @date 2020/4/22 0022 0:34
 */
@Controller
@RequestMapping("/user/kaoyan")
public class KaoYanStudentController {
    //  /kaoyan/index
    @RequestMapping("/index")
    public String index() {

        return "kaoyan-student";
    }
}
