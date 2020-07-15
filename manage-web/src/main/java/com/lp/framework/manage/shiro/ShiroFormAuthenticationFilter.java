package com.lp.framework.manage.shiro;

import com.alibaba.fastjson.JSONObject;
import com.lp.framework.manage.utils.JsonResult;
import org.apache.catalina.connector.RequestFacade;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(ShiroFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }

                return this.executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }
            if(isJson(request)){
                response.setCharacterEncoding("utf-8");
                JsonResult jsonResult = new JsonResult();
                jsonResult.setSuccess(false);
                jsonResult.setMsg("登录超时，请重新登录！");
                response.getWriter().print(JSONObject.toJSONString(jsonResult));
            }else{
                //内嵌页面
                String authc = request.getParameter("authc");
                if("false".equals(authc)){
                    request.setAttribute("accsess",true);
                    return true;
                }else{
                    this.saveRequestAndRedirectToLogin(request, response);
                    return false;
                }
            }
            return false;
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        if(null!=principal){
            String userCode = (String) principal;
            if(subject.hasRole("admin") || "admin".equals(userCode)){
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    private boolean isJson(ServletRequest request){
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String accept = httpServletRequest.getHeader("accept");
        String contentType = request.getContentType();
        if((contentType!=null&&contentType.contains("application/json"))||(accept!=null&&accept.contains("application/json"))){
            return true;
        }else{
            return false;
        }
    }

    protected boolean isSuccessRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getSuccessUrl(), request);
    }
}
