package com.lp.framework.manage.mapper;

import com.lp.framework.manage.model.RoleMenu;

import java.util.List;
import java.util.Map;

public interface RoleMenuMapper {

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    List<Map<String,Object>> selectMenusByPCode(Map<String,Object> params);

    void deleteRoleMenu(Map<String,Object> params);

    void deleteRoleMenuByRoleCode(String roleCode);

    void insertRoleMenu(Map<String,Object> params);

    List<String> selectUserMenus(Map<String,Object> params);

    RoleMenu selectRoleMenuByMenuCode(String menuCode);
}