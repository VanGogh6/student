package com.yuyahong.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuyahong.entity.User;
import com.yuyahong.service.UserService;
import com.yuyahong.utils.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 20:32
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 执行登陆方法
     *
     * @param user
     * @return
     */
    @GetMapping("/login")
    public String login(User user, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (user != null && user.getUsername() != null && user.getPassword() != null) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, user.getUsername());
            //数据库查询到的用户
            User one = userService.getOne(queryWrapper);
            if (one != null) {
                String sqlPassword = one.getPassword();
                String inPassword = user.getPassword();
                if (sqlPassword.equals(MyStringUtils.getPassword(inPassword))) {
                    session.setAttribute("user", one);
                    return "redirect:/index";
                } else {
                    System.out.println("密码不正确");
                    session.setAttribute("loginMsg", "密码不正确");
                }
            } else {
                System.out.println("账户不存");
                session.setAttribute("loginMsg", "账户不存");
            }
        } else {
            model.addAttribute("loginMsg", "输入数据不能为空");
            session.setAttribute("loginMsg", "输入数据不能为空");
        }
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }

}
