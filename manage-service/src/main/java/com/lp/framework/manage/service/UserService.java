package com.lp.framework.manage.service;

import com.lp.framework.manage.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByUserCode(String userCode);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByPage(Map<String,Object> params);

    int updatePwdByPrimaryKey(User user);

}
