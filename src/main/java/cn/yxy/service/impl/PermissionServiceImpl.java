package cn.yxy.service.impl;


import cn.yxy.dao.PermissionMapper;
import cn.yxy.domain.Permission;
import cn.yxy.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public Permission createPermission(Permission permission) {
        permissionMapper.insert(permission);
        return permission;
    }

    public void deletePermission(Long permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }
}
