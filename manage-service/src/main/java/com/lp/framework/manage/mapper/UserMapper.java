package com.lp.framework.manage.mapper;

import com.lp.framework.manage.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int deleteByUserCode(String userCode);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserCode(String userCode);

    List<User> selectByPage(Map<String,Object> params);

    int updatePwdByPrimaryKey(User user);
}