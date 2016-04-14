package com.xinmo.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

public class CustomUserFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
            ServletResponse response, Object mappedValue) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            return subject.isAuthenticated() && subject.getPrincipal() != null;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request,
            ServletResponse response) throws Exception {
        HttpServletRequest httprequest = WebUtils.toHttp(request);
        HttpServletResponse httpresponse = WebUtils.toHttp(response);
        if (httprequest.getHeader("x-requested-with") != null
            && httprequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            // 如果是ajax请求响应头会有，x-requested-with；跳转到登陆页面
            httpresponse.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
        }
        saveRequestAndRedirectToLogin(httprequest, response);
        return false;
    }
}