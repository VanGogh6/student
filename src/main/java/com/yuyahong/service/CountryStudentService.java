package com.yuyahong.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuyahong.entity.CountryStudent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 18:24
 */
public interface CountryStudentService extends IService<CountryStudent> {


    IPage<CountryStudent> getCountryStudentPage(Page<CountryStudent> page, CountryStudent countryStudent);

    void export(HttpServletResponse response, List<CountryStudent> lists);

    void importArmyStudent(MultipartFile file) throws IOException;
}
