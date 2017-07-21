package cn.yxy.service;


import cn.yxy.domain.Role;

/**
 * 接口定义参照 Zhang Kaitao
 */
public interface RoleService {


    Role createRole(Role role);

    void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    void uncorrelationPermissions(Long roleId, Long... permissionIds);

}
