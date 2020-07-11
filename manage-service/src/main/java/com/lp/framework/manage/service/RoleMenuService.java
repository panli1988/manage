package com.lp.framework.manage.service;

import com.lp.framework.manage.model.RoleMenu;

import java.util.List;
import java.util.Map;

public interface RoleMenuService {

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    List<Map<String,Object>> selectMenusByPCode(Map<String,Object> params);

    void deleteRoleMenu(Map<String,Object> params);

    void insertRoleMenu(Map<String,Object> params);

    void updateRoleMenu(Map<String, Object> params);

    List<String> selectUserMenus(Map<String,Object> params);
}
