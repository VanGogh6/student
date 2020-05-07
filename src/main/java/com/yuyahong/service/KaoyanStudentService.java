package com.yuyahong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyahong.entity.KaoyanStudent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 17:50
 */
public interface KaoyanStudentService extends IService<KaoyanStudent> {


    IPage<KaoyanStudent> getWorkStudentPage(Page<KaoyanStudent> page, KaoyanStudent kaoyanStudent);

    void export(HttpServletResponse response, List<KaoyanStudent> lists);

    void importKaoyanStudent(MultipartFile file) throws IOException;
}
