package com.yuyahong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyahong.entity.WorkStudent;
import com.yuyahong.service.WorkStudentService;
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
 * @date 2020/4/22 0022 0:11
 */
@Controller
@RequestMapping("/user/work")
public class WorkStudentController {

    @Autowired
    private WorkStudentService workStudentService;

    /**
     * 分页查询工作学生
     *
     * @param page        分页条件
     * @param workStudent like查询条件
     * @return
     */
    @RequestMapping("/page")
    public String index(Page<WorkStudent> page, WorkStudent workStudent, Model model) {
        IPage<WorkStudent> iPage = workStudentService.getWorkStudentPage(page, workStudent);
        model.addAttribute("iPage", iPage);
        return "/work/page";
    }

    /**
     * 跳转到编辑页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(String id, Model model) {
        WorkStudent student = workStudentService.getById(id);
        model.addAttribute("student", student);
        return "/work/edit";
    }

    /**
     * 开始编辑
     *
     * @param workStudent
     * @return
     */
    @RequestMapping("/edit")
    public String edit(WorkStudent workStudent) {
        workStudentService.updateById(workStudent);
        return "redirect:/user/work/page";//编辑完成，重定向去分页查询
    }


    /**
     * 删除
     *
     * @param id 按学生的id删除
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        workStudentService.removeById(id);
        return "redirect:/user/work/page";
    }

    /**
     * 添加
     *
     * @param workStudent 添加的学生
     * @return
     */
    @RequestMapping("/add")
    public String add(WorkStudent workStudent) {
        workStudentService.save(workStudent);
        return "redirect:/user/work/page";
    }

    /**
     * 导出就业学生
     *
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            List<WorkStudent> lists = workStudentService.list();
            workStudentService.export(response, lists);
        } catch (Exception ignored) {

        }
//        return "redirect:/user/work/page";
    }

    /**
     * 从excel导入学生
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/import")
    public String importWorkStudent(MultipartFile file) throws IOException {
        workStudentService.importWorkStudent(file);
        return "redirect:/user/work/page";
    }
}
