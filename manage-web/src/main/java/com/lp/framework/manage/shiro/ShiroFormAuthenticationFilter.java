package com.lp.framework.manage.shiro;

import com.alibaba.fastjson.JSONObject;
import com.lp.framework.manage.utils.JsonResult;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
                this.saveRequestAndRedirectToLogin(request, response);
            }

            return false;
        }
    }

    private boolean isJson(ServletRequest request){
        String contentType = request.getContentType();
        if(contentType!=null&&contentType.contains("application/json")){
            return true;
        }else{
            return false;
        }
    }
}
