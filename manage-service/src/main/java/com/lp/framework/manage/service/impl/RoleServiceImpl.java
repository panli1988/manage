package com.lp.framework.manage.service.impl;

import com.lp.framework.manage.mapper.RoleMapper;
import com.lp.framework.manage.mapper.RoleMenuMapper;
import com.lp.framework.manage.model.Role;
import com.lp.framework.manage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(Role record) {
        return roleMapper.insert(record);
    }

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Role> selectByPage(Map<String,Object> params) {
        return roleMapper.selectByPage(params);
    }

    @Transactional
    @Override
    public void deleteRole(String roleCode) {
        roleMapper.deleteByRoleCode(roleCode);
        roleMenuMapper.deleteRoleMenuByRoleCode(roleCode);
    }

    @Override
    public Role selectByRoleCode(String roleCode) {
        return roleMapper.selectByRoleCode(roleCode);
    }
}
