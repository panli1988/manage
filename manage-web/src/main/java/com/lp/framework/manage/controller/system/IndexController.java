package com.lp.framework.manage.controller.system;

import com.lp.framework.manage.model.Menu;
import com.lp.framework.manage.model.User;
import com.lp.framework.manage.service.MenuService;
import com.lp.framework.manage.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(){
        return "system/index/index";
    }

    @GetMapping("/main")
    public String main(){
        return "system/index/main";
    }

    @GetMapping("/toLogin")
    public ModelAndView toLogin(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/index/login");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(null!=flashMap&&!flashMap.isEmpty()){
            mv.addAllObjects(flashMap);
        }
        return mv;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, RedirectAttributes model) {
        Map<String, Object> params = WebUtils.getParametersStartingWith(request, "");
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        Subject currentUser = SecurityUtils.getSubject();
        User user = userService.selectByUserCode(username);
        if(null==user) {
            //throw new UnknownAccountException("用户不存在");
            model.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/toLogin";
        }
        if(!currentUser.isAuthenticated()) {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                currentUser.login(token);
            } catch (AuthenticationException e) {
                //e.printStackTrace();
                model.addFlashAttribute("msg","用户名或密码错误");
                return "redirect:/toLogin";
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("name",user.getUserName());
        return "redirect:/index";
    }

    @GetMapping("/getMenus")
    @ResponseBody
    public List<Menu> getMenus(String pCode){
        return menuService.selectMenusByPCode(pCode);
    }

    @GetMapping("/icons")
    public String icons(){
        return "common/icons";
    }
}
