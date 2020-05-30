package com.lp.framework.manage.service;

import com.lp.framework.manage.model.UserRole;

import java.util.List;
import java.util.Map;

public interface UserRoleService {

    int insert(UserRole record);

    int insertSelective(UserRole record);

    int deleteUserRole(UserRole record);

    List<Map<String,Object>> selectUserRoles(Map<String,Object> params);

    void updateUserRoles(Map<String, Object> params);
}
