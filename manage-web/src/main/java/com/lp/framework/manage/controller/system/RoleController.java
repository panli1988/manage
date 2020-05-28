package com.lp.framework.manage.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.framework.manage.model.Role;
import com.lp.framework.manage.service.RoleMenuService;
import com.lp.framework.manage.service.RoleService;
import com.lp.framework.manage.utils.CommonUtils;
import com.lp.framework.manage.utils.JsonResult;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping("/index")
    public String index(){
        return "system/role/roleIndex";
    }


    @GetMapping("/queryPage")
    @ResponseBody
    public JsonResult queryPage(ServletRequest request){
        Map<String, Object> params = CommonUtils.getParametersMap(request);
        JsonResult jsonResult = new JsonResult();
        try {
            int pageNum = Integer.parseInt((String) params.get("pageNum"));
            int pageSize = Integer.parseInt((String) params.get("pageSize"));
            PageHelper.startPage(pageNum,pageSize);
            List<Role> roleList = roleService.selectByPage(params);
            PageInfo<Role> pageInfo = new PageInfo<Role>(roleList);
            jsonResult = new JsonResult(pageInfo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("查询失败");
        }
        return jsonResult;
    }

    @PostMapping("/insert")
    @ResponseBody
    public JsonResult insert(ServletRequest request,@RequestBody Role role){
        JsonResult jsonResult = new JsonResult();
        try {
            roleService.insert(role);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestBody Role role){
        JsonResult jsonResult = new JsonResult();
        try {
            roleService.updateByPrimaryKeySelective(role);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @GetMapping("/deleteById")
    @ResponseBody
    public JsonResult deleteById(Integer roleId){
        JsonResult jsonResult = new JsonResult();
        try {
            roleService.deleteByPrimaryKey(roleId);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("删除失败");
        }
        return jsonResult;
    }

    @GetMapping("/getMenus")
    @ResponseBody
    public List<Map<String,Object>> getMenus(ServletRequest request){
        Map<String, Object> params = CommonUtils.getParametersMap(request);
        List<Map<String,Object>> returnList = new ArrayList<>();
        List<Map<String, Object>> menuList = roleMenuService.selectMenusByPCode(params);
        for(Map<String,Object> map:menuList){
            params.put("pCode",map.get("menuCode"));
            List<Map<String, Object>> list = roleMenuService.selectMenusByPCode(params);
            if(ObjectUtils.isNotEmpty(list)){
                map.put("menus",list);
            }
            returnList.add(map);
        }
        return returnList;
    }

    @PostMapping("/updateRoleMenu")
    @ResponseBody
    public JsonResult updateRoleMenu(@RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            roleMenuService.updateRoleMenu(params);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

}
