package cn.yxy.dao;

import cn.yxy.domain.RolePermissionKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionMapper {
    int delete(RolePermissionKey record);

    int insert(RolePermissionKey record);

    boolean exists(RolePermissionKey record);
}