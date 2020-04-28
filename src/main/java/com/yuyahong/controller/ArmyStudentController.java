package com.yuyahong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yuyahong
 * @date 2020/4/22 0022 0:37
 */
@Controller
@RequestMapping("/user/army")
public class ArmyStudentController {

    @RequestMapping("/page")
    public ModelAndView page(ModelAndView modelAndView) {
        modelAndView.setViewName("army-student");
        return modelAndView;
    }

}
