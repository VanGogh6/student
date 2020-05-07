package com.yuyahong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyahong.entity.OtherStudent;
import com.yuyahong.service.OtherStudentService;
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
 * @date 2020/4/22 0022 0:44
 */
@Controller
@RequestMapping("/user/other")
public class OtherStudentController {
    @Autowired
    private OtherStudentService otherStudentService;

    @RequestMapping("/page")
    public String page(Page<OtherStudent> page, OtherStudent otherStudent, Model model) {
        IPage<OtherStudent> iPage = otherStudentService.getOtherStudentPage(page, otherStudent);
        model.addAttribute("iPage", iPage);
        return "/other/page";
    }

    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            List<OtherStudent> lists = otherStudentService.list();
            otherStudentService.export(response, lists);
        } catch (Exception ignored) {

        }
    }

    @RequestMapping("/import")
    public String importS(MultipartFile file) throws IOException {
        otherStudentService.importOtherStudent(file);
        return "redirect:/user/other/page";
    }


    @RequestMapping("/delete")
    public String delete(String id) {
        otherStudentService.removeById(id);
        return "redirect:/user/other/page";
    }

    @RequestMapping("/toEdit")
    public String toEdit(String id, Model model) {
        OtherStudent student = otherStudentService.getById(id);
        model.addAttribute("student", student);
        return "/other/edit";
    }

    @RequestMapping("/edit")
    public String edit(OtherStudent student) {//
        otherStudentService.updateById(student);
        return "redirect:/user/other/page";
    }

    @RequestMapping("/add")
    public String add(OtherStudent student) {
        otherStudentService.save(student);
        return "redirect:/user/other/page";
    }

}
