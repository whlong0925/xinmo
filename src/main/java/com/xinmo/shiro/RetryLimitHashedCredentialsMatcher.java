package com.xinmo.shiro;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.xinmo.util.SecurityUtil;

public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher  {

    private Cache<String, AtomicInteger> passwordRetryCache;

    private String keyPrefix = "retry-cache:";
    
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("shiro-password-retry-cache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        
        String username = token.getUsername();
        
        String key = this.keyPrefix + username;
        //retry count + 1
        AtomicInteger retryCount = this.passwordRetryCache.get(key);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            this.passwordRetryCache.put(key, retryCount);
        }
        int count = retryCount.incrementAndGet();
        if(count > 5) {
            throw new ExcessiveAttemptsException("登陆失败次数超过5次，账号被锁定");
        }
        
        boolean matches = false;
        try {
            Object tokenCredentials = SecurityUtil.md5(String.valueOf(token.getPassword()));
            Object accountCredentials = getCredentials(info);
            //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
            matches = equals(tokenCredentials, accountCredentials);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(matches) {
            this.passwordRetryCache.remove(key);
        }else{
            this.passwordRetryCache.put(key, new AtomicInteger(count)); 
        }
        return matches;
    }
}
