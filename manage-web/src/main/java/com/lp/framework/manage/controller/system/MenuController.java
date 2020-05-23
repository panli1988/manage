package com.lp.framework.manage.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.framework.manage.model.Authority;
import com.lp.framework.manage.model.Menu;
import com.lp.framework.manage.model.Role;
import com.lp.framework.manage.service.MenuService;
import com.lp.framework.manage.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/index")
    public String index(){
        return "system/menu/menuIndex";
    }

    @GetMapping("/queryPage")
    @ResponseBody
    public JsonResult queryPage(ServletRequest request){
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, "");
        JsonResult jsonResult = null;
        try {
            int pageNum = Integer.parseInt((String) params.get("pageNum"));
            int pageSize = Integer.parseInt((String) params.get("pageSize"));
            PageHelper.startPage(pageNum,pageSize);
            if(null!=params&&params.keySet().size()<=2){
                params.put("pCode","m_00");
            }
            List<Menu> menuList = menuService.selectByPage(params);
            PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuList);
            jsonResult = new JsonResult(pageInfo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("查询失败");
        }
        return jsonResult;
    }

    @GetMapping("/queryByPCode")
    @ResponseBody
    public List<Menu> queryByPCode(String pCode){
        return menuService.selectMenusByPCode(pCode);
    }

    @PostMapping("/insert")
    @ResponseBody
    public JsonResult insert(ServletRequest request,@RequestBody Menu menu){
        JsonResult jsonResult = new JsonResult();
        try {
            menuService.insert(menu);
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
    public JsonResult update(ServletRequest request,@RequestBody Menu menu){
        JsonResult jsonResult = new JsonResult();
        try {
            menuService.updateByPrimaryKeySelective(menu);
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
    public JsonResult deleteById(Integer menuId,String pCode){
        JsonResult jsonResult = new JsonResult();
        try {
            List<Menu> menus = menuService.selectMenusByPCode(pCode);
            if(menus!=null&&!menus.isEmpty()){
                jsonResult.setSuccess(false);
                jsonResult.setMsg("存在子级，请先删除子级");
                return jsonResult;
            }
            menuService.deleteByPrimaryKey(menuId);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("删除失败");
        }
        return jsonResult;
    }
}
