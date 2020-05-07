package com.yuyahong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyahong.entity.EntrepreneurshipStudent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 17:50
 */
public interface EntrepreneurshipStudentService extends IService<EntrepreneurshipStudent> {


    IPage<EntrepreneurshipStudent> getEntrepreneurshipStudentPage(Page<EntrepreneurshipStudent> page, EntrepreneurshipStudent entrepreneurshipStudent);

    void export(HttpServletResponse response, List<EntrepreneurshipStudent> lists);

    void importEntrepreneurshipStudent(MultipartFile file) throws IOException;
}
