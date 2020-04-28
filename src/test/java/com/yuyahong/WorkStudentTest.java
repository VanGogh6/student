package com.yuyahong;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuyahong.entity.WorkStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yuyahong
 * @date 2020/4/27 0027 11:19
 */
@SpringBootTest
public class WorkStudentTest {

    @Autowired
    private BaseMapper<WorkStudent> baseMapper;

    @Test
    public void test() {
//        IPage<WorkStudent> page = new Page<>();
//        page.setSize(5);
//        page.setCurrent(1);
//        LambdaQueryWrapper<WorkStudent> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.like(WorkStudent::getWorkAddress, "");
//        IPage<WorkStudent> iPage = baseMapper.selectPage(page, queryWrapper);
    }

}
