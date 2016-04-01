package com.xinmo.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class CustomAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
            ServletResponse response, Object mappedValue) throws Exception {
        String requestURI = getPathWithinApplication(request);
        Subject subject = getSubject(request, response);
        return subject.isPermitted(requestURI);
    }

}
