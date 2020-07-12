package com.lp.framework.manage.mapper;

import com.lp.framework.manage.model.UserRole;

import java.util.List;
import java.util.Map;


public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    int deleteUserRole(UserRole record);

    List<Map<String,Object>> selectUserRoles(Map<String,Object> params);

    List<String> selectRoles(Map<String,Object> params);

    int deleteByUserCode(String userCode);

    int deleteByRoleCode(String roleCode);

    UserRole selectByRoleCode(String roleCode);
}