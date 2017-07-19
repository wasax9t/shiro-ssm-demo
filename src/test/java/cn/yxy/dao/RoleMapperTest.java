package cn.yxy.dao;

import cn.yxy.BaseTest;
import cn.yxy.domain.Role;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/19.
 */
public class RoleMapperTest extends BaseTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void curd() throws Exception {
        Role role = new Role();
        role.setRole("超级管理员");
        role.setDescription("");
        role.setAvailable(true);
        roleMapper.insert(role);
        Long id = role.getId();

        Role r2 = roleMapper.selectByPrimaryKey(id);
        Assert.assertNotNull(r2);

        role.setAvailable(false);
        int i = roleMapper.updateByPrimaryKey(role);
        Assert.assertEquals(i, 1);

        i = roleMapper.deleteByPrimaryKey(id);
        Assert.assertEquals(i, 1);
        Role r3 = roleMapper.selectByPrimaryKey(id);
        Assert.assertNull(r3);
    }


}