package com.yuyahong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyahong.entity.CountryStudent;
import com.yuyahong.service.CountryStudentService;
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
 * 公务员学生
 *
 * @author yuyahong
 * @date 2020/4/22 0022 0:42
 */
@RequestMapping("/user/country")
@Controller
public class CountryStudentController {
    @Autowired
    private CountryStudentService countryStudentService;

    @RequestMapping("/page")
    public String page(Page<CountryStudent> page, CountryStudent CountryStudent, Model model) {
        IPage<CountryStudent> iPage = countryStudentService.getCountryStudentPage(page, CountryStudent);
        model.addAttribute("iPage", iPage);
        return "/country/page";
    }

    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            List<CountryStudent> lists = countryStudentService.list();
            countryStudentService.export(response, lists);
        } catch (Exception ignored) {

        }
    }

    @RequestMapping("/import")
    public String importS(MultipartFile file) throws IOException {
        countryStudentService.importArmyStudent(file);
        return "redirect:/user/country/page";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        countryStudentService.removeById(id);
        return "redirect:/user/country/page";
    }

    @RequestMapping("/toEdit")
    public String toEdit(String id, Model model) {
        CountryStudent student = countryStudentService.getById(id);
        model.addAttribute("student", student);
        return "/country/edit";
    }

    @RequestMapping("/edit")
    public String edit(CountryStudent student) {
        countryStudentService.updateById(student);
        return "redirect:/user/country/page";
    }

    @RequestMapping("/add")
    public String add(CountryStudent student) {
        countryStudentService.save(student);
        return "redirect:/user/country/page";
    }
}
