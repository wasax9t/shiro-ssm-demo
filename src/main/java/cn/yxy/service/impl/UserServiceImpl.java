package cn.yxy.service.impl;

import cn.yxy.dao.UserMapper;
import cn.yxy.domain.User;
import cn.yxy.service.UserService;
import cn.yxy.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EncryptUtil encryptUtil;

    @Override
    public User createUser(User user) {
        encryptUtil.encryptPassword(user);
        userMapper.insert(user);
        return user;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {

    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Set<String> findRoles(String username) {
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
