package com.lp.framework.manage.service;

import com.lp.framework.manage.model.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectMenusByPCode(Map<String,Object> params);

    List<Menu> selectByPage(Map<String,Object> params);

    List<Menu> selectUserMenusByPCode(Map<String,Object> params);
}
