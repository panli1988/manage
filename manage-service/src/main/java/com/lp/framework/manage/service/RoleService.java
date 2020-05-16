package com.lp.framework.manage.service;

import com.lp.framework.manage.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByPage(Map<String,Object> params);
}
