package com.xinmo.shiro;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

public class KickoutSessionControlFilter extends AccessControlFilter {

    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter = false; //踢出之前/之后登录的用户  默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1
    private String keyPrefix = "kickout-";
    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        
        Session session = subject.getSession();
        String username = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        String key = this.keyPrefix + username;
        //TODO 同步控制
        Deque<Serializable> deque = this.cache.get(key);
        if(deque == null) {
            deque = new LinkedList<>();
        }

        if(session.getAttribute("kickout") != null){
          //会话被踢出了
            try {
                subject.logout();
                HttpServletRequest httprequest = WebUtils.toHttp(request);
                if (httprequest.getHeader("x-requested-with") != null && httprequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { 
                    // 如果是ajax请求响应头会有，x-requested-with；跳转到登陆页面
                    WebUtils.toHttp(response).setHeader("sessionstatus", "timeout");// 在响应头设置session状态
                }
                WebUtils.issueRedirect(request, response, this.kickoutUrl);
            } catch (Exception e) { //ignore
                e.printStackTrace();
            }
            return false;
        }else if(!deque.contains(sessionId)){
            deque.push(sessionId);
        }
        
        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > this.maxSession) {
            Serializable kickoutSessionId = null;
            if(this.kickoutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst();
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession = this.sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
                e.printStackTrace();
            }
        }
        this.cache.put(key, deque);
        return true;
    }
}
