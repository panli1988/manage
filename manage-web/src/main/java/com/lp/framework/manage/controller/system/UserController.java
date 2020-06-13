package com.lp.framework.manage.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.framework.manage.model.User;
import com.lp.framework.manage.service.UserRoleService;
import com.lp.framework.manage.service.UserService;
import com.lp.framework.manage.utils.CommonUtils;
import com.lp.framework.manage.utils.JsonResult;
import com.lp.framework.manage.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/index")
    public String index(){
        return "system/user/userIndex";
    }

    @GetMapping("/queryPage")
    @ResponseBody
    public JsonResult queryPage(ServletRequest request){
        logger.info("测试日志==================={}","test");
        Map<String, Object> params = CommonUtils.getParametersMap(request);
        JsonResult jsonResult = new JsonResult();
        try {
            int pageNum = Integer.parseInt((String) params.get("pageNum"));
            int pageSize = Integer.parseInt((String) params.get("pageSize"));
            PageHelper.startPage(pageNum,pageSize);
            List<User> userList = userService.selectByPage(params);
            PageInfo<User> pageInfo = new PageInfo<User>(userList);
            jsonResult = new JsonResult(pageInfo);
        } catch (NumberFormatException e) {
            logger.error("用户查询失败：",e);
            jsonResult.setSuccess(false);
            jsonResult.setMsg("查询失败");
        }
        return jsonResult;
    }

    @PostMapping("/insert")
    @ResponseBody
    public JsonResult insert(ServletRequest request,@RequestBody User user){
        JsonResult jsonResult = new JsonResult();
        String pwd = ShiroUtils.encrypt("123456",user.getUserCode());
        user.setPwd(pwd);
        try {
            userService.insert(user);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            logger.error("新增用户失败：",e);
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestBody User user){
        JsonResult jsonResult = new JsonResult();
        try {
            userService.updateByPrimaryKeySelective(user);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            logger.error("更新用户失败：",e);
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }
    @PostMapping("/resetPwd")
    @ResponseBody
    public JsonResult resetPwd(@RequestBody User user){
        JsonResult jsonResult = new JsonResult();
        String pwd = ShiroUtils.encrypt(user.getPwd(),user.getUserCode());
        user.setPwd(pwd);
        try {
            userService.updatePwdByPrimaryKey(user);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            logger.error("重置密码失败：",e);
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @GetMapping("/getUserRoles")
    @ResponseBody
    public List<Map<String,Object>> getUserRoles(ServletRequest request){
        Map<String, Object> params = CommonUtils.getParametersMap(request);
        return userRoleService.selectUserRoles(params);
    }

    @PostMapping("/updateUserRoles")
    @ResponseBody
    public JsonResult updateUserRoles(@RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            userRoleService.updateUserRoles(params);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            logger.error("配置用户角色失败：",e);
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }
}
