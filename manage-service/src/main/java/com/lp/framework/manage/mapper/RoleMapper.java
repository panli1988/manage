package com.lp.framework.manage.mapper;

import com.lp.framework.manage.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleId);

    int deleteByRoleCode(String roleCode);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    Role selectByRoleCode(String roleCode);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByPage(Map<String,Object> params);
}