package com.yuyahong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 16:57
 */
@RestController
public class TestController {

    @RequestMapping("hello")
    public Object test(){
        return "hello word!";
    }

}
