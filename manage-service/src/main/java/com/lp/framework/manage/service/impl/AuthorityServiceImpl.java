package com.lp.framework.manage.service.impl;

import com.lp.framework.manage.mapper.AuthorityMapper;
import com.lp.framework.manage.model.Authority;
import com.lp.framework.manage.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public int deleteByPrimaryKey(Integer authorityId) {
        return authorityMapper.deleteByPrimaryKey(authorityId);
    }

    @Override
    public int insert(Authority record) {
        return authorityMapper.insert(record);
    }

    @Override
    public int insertSelective(Authority record) {
        return authorityMapper.insertSelective(record);
    }

    @Override
    public Authority selectByPrimaryKey(Integer authorityId) {
        return authorityMapper.selectByPrimaryKey(authorityId);
    }

    @Override
    public int updateByPrimaryKeySelective(Authority record) {
        return authorityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Authority record) {
        return authorityMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Authority> selectByPage(Map<String,Object> params) {
        return authorityMapper.selectByPage(params);
    }
}
