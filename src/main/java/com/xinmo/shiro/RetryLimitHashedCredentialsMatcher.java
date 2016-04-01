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

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        //retry count + 1
        AtomicInteger retryCount = this.passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            this.passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
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
            this.passwordRetryCache.remove(username);
        }
        return matches;
    }
}
