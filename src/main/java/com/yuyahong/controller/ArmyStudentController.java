package com.yuyahong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyahong.entity.ArmyStudent;
import com.yuyahong.service.ArmyStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yuyahong
 * @date 2020/4/22 0022 0:37
 */
@Controller
@RequestMapping("/user/army")
public class ArmyStudentController {
    @Autowired
    private ArmyStudentService armyStudentService;

    @RequestMapping("/page")
    public String page(Page<ArmyStudent> page, ArmyStudent armyStudent, Model model) {
        IPage<ArmyStudent> iPage = armyStudentService.getArmyStudentPage(page, armyStudent);
        model.addAttribute("iPage", iPage);
        return "/army/page";
    }

    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            List<ArmyStudent> lists = armyStudentService.list();
            armyStudentService.export(response, lists);
        } catch (Exception ignored) {

        }
    }

    @RequestMapping("/import")
    public String importS(MultipartFile file) throws IOException {
        armyStudentService.importArmyStudent(file);
        return "redirect:/user/army/page";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        armyStudentService.removeById(id);
        return "redirect:/user/army/page";
    }

    @RequestMapping("/toEdit")
    public String toEdit(String id, Model model) {
        ArmyStudent student = armyStudentService.getById(id);
        model.addAttribute("student", student);
        return "/army/edit";
    }

    @RequestMapping("/edit")
    public String edit(ArmyStudent student) {
        armyStudentService.updateById(student);
        return "redirect:/user/army/page";
    }

    @RequestMapping("/add")
    public String add(ArmyStudent student) {
        armyStudentService.save(student);
        return "redirect:/user/army/page";
    }

}
