package com.yuyahong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuyahong
 * @date 2020/4/22 0022 0:37
 */
@Controller
@RequestMapping("/army")
public class ArmyStudentController {

    @RequestMapping("/index")
    public String index() {
        return "army-student";
    }

}
