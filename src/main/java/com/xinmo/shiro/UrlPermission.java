package com.xinmo.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

public class UrlPermission implements Permission  {

    private String url;
    public UrlPermission(){
        
    }
    public UrlPermission(String url) {
        super();
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean implies(Permission p) {
        if(!(p instanceof UrlPermission)){
            return false;
        }
        UrlPermission up = (UrlPermission)p;
        PatternMatcher patternMatchere = new AntPathMatcher();
        return patternMatchere.matches(this.getUrl(), up.getUrl());
    }
   
}
