package cn.yxy.service.impl;

import cn.yxy.dao.RoleMapper;
import cn.yxy.dao.RolePermissionMapper;
import cn.yxy.domain.Role;
import cn.yxy.domain.RolePermissionKey;
import cn.yxy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public Role createRole(Role role) {
        roleMapper.insert(role);
        return role;
    }

    public void deleteRole(Long roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length == 0) {
            return;
        }

        RolePermissionKey rolePermissionKey = new RolePermissionKey();
        rolePermissionKey.setRoleId(roleId);
        for (Long permissionId : permissionIds) {
            if (!rolePermissionMapper.exists(rolePermissionKey)) {
                rolePermissionKey.setPermissionId(permissionId);
                rolePermissionMapper.insert(rolePermissionKey);
            }
        }
    }

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        RolePermissionKey rolePermissionKey = new RolePermissionKey();
        rolePermissionKey.setRoleId(roleId);
        for (Long permissionId : permissionIds) {
            rolePermissionKey.setPermissionId(permissionId);
            rolePermissionMapper.delete(rolePermissionKey);
        }
    }

}
