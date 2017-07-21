package cn.yxy.service;

import cn.yxy.domain.Permission;

/**
 * 接口定义参照 Zhang Kaitao
 */
public interface PermissionService {
    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
