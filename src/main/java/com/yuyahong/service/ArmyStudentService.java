package com.yuyahong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyahong.entity.ArmyStudent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 17:50
 */
public interface ArmyStudentService extends IService<ArmyStudent> {


    IPage<ArmyStudent> getArmyStudentPage(Page<ArmyStudent> page, ArmyStudent armyStudent);

    void export(HttpServletResponse response, List<ArmyStudent> lists);


    void importArmyStudent(MultipartFile file) throws IOException;
}
