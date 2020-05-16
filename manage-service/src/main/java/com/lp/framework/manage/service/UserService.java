package com.lp.framework.manage.service;

import com.lp.framework.manage.model.User;

public interface UserService {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByUserCode(String userCode);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
