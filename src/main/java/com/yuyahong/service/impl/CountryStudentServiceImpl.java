package com.yuyahong.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyahong.entity.CountryStudent;
import com.yuyahong.mapper.CountryStudentMapper;
import com.yuyahong.service.CountryStudentService;
/** 
 * @author wufan
 * @date 2020/4/21 0021 18:24
 */  
@Service
public class CountryStudentServiceImpl extends ServiceImpl<CountryStudentMapper, CountryStudent> implements CountryStudentService{

}
