package com.lp.framework.manage.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.framework.manage.model.Authority;
import com.lp.framework.manage.service.AuthorityService;
import com.lp.framework.manage.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;


    @GetMapping("/index")
    private String index(){
        return "system/authority/authorityIndex";
    }

    @GetMapping("/queryPage")
    @ResponseBody
    public JsonResult queryPage(ServletRequest request){
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, "");
        int pageNum = Integer.parseInt((String) params.get("pageNum"));
        int pageSize = Integer.parseInt((String) params.get("pageSize"));
        PageHelper.startPage(pageNum,pageSize);
        List<Authority> authorityList = authorityService.selectByPage(params);
        PageInfo<Authority> pageInfo = new PageInfo<Authority>(authorityList);
        JsonResult jsonResult = new JsonResult(pageInfo);
        return jsonResult;
    }

    @GetMapping("/update")
    @ResponseBody
    public JsonResult update(Authority authority){
        JsonResult jsonResult = new JsonResult();
        try {
            authorityService.updateByPrimaryKeySelective(authority);
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
    public JsonResult deleteById(int authorityId){
        JsonResult jsonResult = new JsonResult();
        try {
            authorityService.deleteByPrimaryKey(authorityId);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }



    @GetMapping("/authority")
    @ResponseBody
    public Authority test(){
        Authority authority = authorityService.selectByPrimaryKey(2);
        System.out.println(authority);
        return authority;
    }

    @GetMapping("/page")
    @ResponseBody
    public JsonResult page(){
        PageHelper.startPage(2,5);
        List<Authority> authorityList = authorityService.selectByPage(null);
        PageInfo<Authority> pageInfo = new PageInfo<Authority>(authorityList);
        JsonResult jsonResult = new JsonResult(pageInfo);
        return jsonResult;
    }

}
