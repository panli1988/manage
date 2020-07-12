package com.lp.framework.manage.service.impl;

import com.lp.framework.manage.mapper.UserRoleMapper;
import com.lp.framework.manage.model.UserRole;
import com.lp.framework.manage.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(UserRole record) {
        return userRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(UserRole record) {
        return userRoleMapper.insertSelective(record);
    }

    @Override
    public int deleteUserRole(UserRole record) {
        return userRoleMapper.deleteUserRole(record);
    }

    @Override
    public List<Map<String, Object>> selectUserRoles(Map<String, Object> params) {
        return userRoleMapper.selectUserRoles(params);
    }

    @Override
    @Transactional
    public void updateUserRoles(Map<String, Object> params) {
        List<String> addList = (List<String>) params.get("addArr");
        List<String> delList = (List<String>) params.get("delArr");
        String userCode = (String) params.get("userCode");
        UserRole userRole = null;
        for(String roleCode:addList){
            userRole = new UserRole(userCode,roleCode);
            userRoleMapper.insert(userRole);
        }
        for(String roleCode:delList){
            userRole = new UserRole(userCode,roleCode);
            userRoleMapper.deleteUserRole(userRole);
        }
    }

    @Override
    public List<String> selectRoles(Map<String, Object> params) {
        return userRoleMapper.selectRoles(params);
    }

    @Override
    public int deleteByUserCode(String userCode) {
        return userRoleMapper.deleteByUserCode(userCode);
    }

    @Override
    public int deleteByRoleCode(String roleCode) {
        return userRoleMapper.deleteByRoleCode(roleCode);
    }

    @Override
    public UserRole selectByRoleCode(String roleCode) {
        return userRoleMapper.selectByRoleCode(roleCode);
    }
}
