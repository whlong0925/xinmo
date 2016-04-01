package com.xinmo.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 自定义异常处理
 * @author Administrator
 *
 */
public class MyHandlerExceptionResolver extends SimpleMappingExceptionResolver{

	@Override
    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
            ex.printStackTrace();
            return super.doResolveException(request,response,handler,ex);
    }

}