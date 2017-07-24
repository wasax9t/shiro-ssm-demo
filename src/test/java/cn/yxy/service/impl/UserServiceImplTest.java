package cn.yxy.service.impl;

import cn.yxy.BaseTest;
import cn.yxy.domain.User;
import cn.yxy.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/7/21.
 */
@Ignore
public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        userService.createUser(user);
    }

    @Test
    public void findByUsername() throws Exception {
        User user = userService.findByUsername("admin");
        System.out.println(user);
    }


}