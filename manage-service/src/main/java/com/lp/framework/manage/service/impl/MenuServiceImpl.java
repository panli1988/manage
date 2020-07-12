package com.lp.framework.manage.service.impl;

import com.lp.framework.manage.mapper.MenuMapper;
import com.lp.framework.manage.model.Menu;
import com.lp.framework.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(Integer menuId) {
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public int insert(Menu record) {
        return menuMapper.insert(record);
    }

    @Override
    public int insertSelective(Menu record) {
        return menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return menuMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Menu> selectMenusByPCode(Map<String,Object> params) {
        List<Menu> returnList = new ArrayList<Menu>();
        List<Menu> menuList = menuMapper.selectMenusByPCode(params);
        for (Menu menu : menuList) {
            //采用递归
            params.put("pCode", menu.getMenuCode());
            List<Menu> menus = this.selectMenusByPCode(params);
            menu.setMenus(menus.size()>0?menus:null);
            returnList.add(menu);
        }
        return returnList;
    }

    @Override
    public List<Menu> selectByPage(Map<String,Object> params) {
        return menuMapper.selectByPage(params);
    }

    @Override
    public List<Menu> selectUserMenusByPCode(Map<String, Object> params) {
        List<Menu> returnList = new ArrayList<Menu>();
        List<Menu> menuList = menuMapper.selectUserMenusByPCode(params);
        for (Menu menu : menuList) {
            //采用递归
            params.put("pCode",menu.getMenuCode());
            List<Menu> menus = this.selectUserMenusByPCode(params);
            menu.setMenus(menus.size()>0?menus:null);
            returnList.add(menu);
        }
        return returnList;
    }

    @Override
    public int deleteByMenuCode(String menuCode) {
        return menuMapper.deleteByMenuCode(menuCode);
    }

    @Override
    public Menu selectByMenuCode(String menuCode) {
        return menuMapper.selectByMenuCode(menuCode);
    }
}
