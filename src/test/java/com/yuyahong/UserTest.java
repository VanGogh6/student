package com.yuyahong;

import com.yuyahong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 20:36
 */
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;

//    @Test
//    public void saveUserTest() {
//        User user = new User();
//        user.setUsername("admin");
//        user.setPassword(MyStringUtils.getPassword("123456"));
//        user.setRealName("管理员");
//        userService.save(user);
//    }

}
