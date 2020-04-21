package com.yuyahong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyahong.entity.User;
import com.yuyahong.mapper.UserMapper;
import com.yuyahong.service.UserService;
import org.springframework.stereotype.Service;
/** 
 * @author yuyahong
 * @date 2020/4/21 0021 19:08
 */  
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
