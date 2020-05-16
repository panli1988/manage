package com.lp.framework.manage.mapper;

import com.lp.framework.manage.model.Authority;

import java.util.List;
import java.util.Map;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer authorityId);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer authorityId);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    List<Authority> selectByPage(Map<String,Object> params);
}