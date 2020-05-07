package com.yuyahong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyahong.entity.EntrepreneurshipStudent;
import com.yuyahong.service.EntrepreneurshipStudentService;
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
 * @date 2020/4/22 0022 0:49
 */
@RequestMapping("/user/chuangye")
@Controller
public class EntrepreneurshipStudentController {
    @Autowired
    private EntrepreneurshipStudentService entrepreneurshipStudentService;

    @RequestMapping("/page")
    public String page(Page<EntrepreneurshipStudent> page, EntrepreneurshipStudent entrepreneurshipStudent, Model model) {
        IPage<EntrepreneurshipStudent> iPage = entrepreneurshipStudentService.getEntrepreneurshipStudentPage(page, entrepreneurshipStudent);
        model.addAttribute("iPage", iPage);
        return "/chuangye/page";
    }


    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            List<EntrepreneurshipStudent> lists = entrepreneurshipStudentService.list();
            entrepreneurshipStudentService.export(response, lists);
        } catch (Exception ignored) {

        }
    }

    @RequestMapping("/import")
    public String importS(MultipartFile file) throws IOException {
        entrepreneurshipStudentService.importEntrepreneurshipStudent(file);
        return "redirect:/user/chuangye/page";
    }


    @RequestMapping("/delete")
    public String delete(String id) {
        entrepreneurshipStudentService.removeById(id);
        return "redirect:/user/chuangye/page";
    }

    @RequestMapping("/toEdit")
    public String toEdit(String id, Model model) {
        EntrepreneurshipStudent student = entrepreneurshipStudentService.getById(id);
        model.addAttribute("student", student);
        return "/chuangye/edit";
    }

    @RequestMapping("/edit")
    public String edit(EntrepreneurshipStudent student) {
        entrepreneurshipStudentService.updateById(student);
        return "redirect:/user/chuangye/page";
    }

    @RequestMapping("/add")
    public String add(EntrepreneurshipStudent student) {
        entrepreneurshipStudentService.save(student);
        return "redirect:/user/chuangye/page";
    }

}
