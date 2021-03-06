package com.lp.framework.manage.service.impl;

import com.lp.framework.manage.mapper.RoleMenuMapper;
import com.lp.framework.manage.model.RoleMenu;
import com.lp.framework.manage.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public int insert(RoleMenu record) {
        return roleMenuMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleMenu record) {
        return roleMenuMapper.insert(record);
    }

    @Override
    public List<Map<String, Object>> selectMenusByPCode(Map<String, Object> params) {
        return roleMenuMapper.selectMenusByPCode(params);
    }

    @Override
    public void deleteRoleMenu(Map<String, Object> params) {
        roleMenuMapper.deleteRoleMenu(params);
    }

    @Override
    public void insertRoleMenu(Map<String, Object> params) {
        roleMenuMapper.insertRoleMenu(params);
    }

    @Override
    @Transactional
    public void updateRoleMenu(Map<String, Object> params) {
        List<String> addList = (List<String>) params.get("addArr");
        List<String> delList = (List<String>) params.get("delArr");
        String roleCode = (String) params.get("roleCode");
        Map<String,Object> map = new HashMap<>();
        map.put("roleCode",roleCode);
        for(String menuCode:addList){
            map.put("menuCode",menuCode);
            this.insertRoleMenu(map);
        }
        for(String menuCode:delList){
            map.put("menuCode",menuCode);
            this.deleteRoleMenu(map);
        }
    }

    @Override
    public List<String> selectUserMenus(Map<String, Object> params) {
        return roleMenuMapper.selectUserMenus(params);
    }

    @Override
    public RoleMenu selectRoleMenuByMenuCode(String menuCode) {
        return roleMenuMapper.selectRoleMenuByMenuCode(menuCode);
    }

    @Override
    public List<Map<String, Object>> selectMenusTreeByPCode(Map<String, Object> params) {
        List<Map<String,Object>> returnList = new ArrayList<>();
        List<Map<String, Object>> mapList = roleMenuMapper.selectMenusByPCode(params);
        for(Map map:mapList){
            params.put("pCode",map.get("menuCode"));
            List<Map<String, Object>> list = this.selectMenusTreeByPCode(params);
            map.put("menus",list.size()>0?list:null);
            returnList.add(map);
        }
        return returnList;
    }

}
