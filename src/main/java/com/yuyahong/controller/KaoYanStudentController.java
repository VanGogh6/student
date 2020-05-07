package com.yuyahong.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuyahong.entity.KaoyanStudent;
import com.yuyahong.service.KaoyanStudentService;
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
 * 考研学生
 * 参照就业学生
 *
 * @author yuyahong
 * @date 2020/4/22 0022 0:34
 */
@Controller
@RequestMapping("/user/kaoyan")
public class KaoYanStudentController {
    //注入KaoyanStudentService的实现类的对象
    @Autowired
    private KaoyanStudentService kaoyanStudentService;

    /**
     * 分页显示就业学生
     *
     * @param page          分页条件
     * @param kaoyanStudent 前端传入的查询考研学生的条件
     * @param model         存放返回前端的数据
     * @return
     */
    @RequestMapping("/page")
    public String page(Page<KaoyanStudent> page, KaoyanStudent kaoyanStudent, Model model) {
        //查询到数据信息
        IPage<KaoyanStudent> iPage = kaoyanStudentService.getWorkStudentPage(page, kaoyanStudent);
        //存储到Model里返回前端
        model.addAttribute("iPage", iPage);
        return "/kaoyan/page";
    }


    /**
     * 添加方法
     *
     * @return
     */
    @RequestMapping("add")
    public String add(KaoyanStudent kaoyanStudent) {
        System.out.println("输入的KaoyanStudent.getKaoyanSchool=" + kaoyanStudent.getKaoyanSchool());
        kaoyanStudentService.save(kaoyanStudent);
        return "redirect:/user/kaoyan/page";//添加完成后重定向去一次分页查询
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(String id) {
        kaoyanStudentService.removeById(id);
        return "redirect:/user/kaoyan/page";//删除完成后重定向去一次分页查询
    }


    /**
     * 回传参数到编辑页面
     * 去编辑页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEdit(String id, Model model) {
        KaoyanStudent student = kaoyanStudentService.getById(id);
        model.addAttribute("student", student);
        return "/kaoyan/edit";
    }

    @RequestMapping("/edit")
    public String edit(KaoyanStudent kaoyanStudent) {
        boolean b = kaoyanStudentService.updateById(kaoyanStudent);
        System.out.println(b);
        return "redirect:/user/kaoyan/page";//编辑完成后重定向去一次分页查询
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
            List<KaoyanStudent> lists = kaoyanStudentService.list();
            kaoyanStudentService.export(response, lists);
        } catch (Exception ignored) {

        }
//        return "redirect:/user/kaoyan/page";//编辑完成后重定向去一次分页查询
    }

    //import
    @RequestMapping("/import")
    public String importD(MultipartFile file) throws IOException {
        kaoyanStudentService.importKaoyanStudent(file);
        return "redirect:/user/kaoyan/page";//编辑完成后重定向去一次分页查询
    }
}
