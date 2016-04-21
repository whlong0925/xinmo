package com.xinmo.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.xinmo.entity.Function;
import com.xinmo.entity.Role;
import com.xinmo.entity.User;
import com.xinmo.service.RoleService;
import com.xinmo.service.UserService;
import com.xinmo.util.Constants;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public static final String AUTHORIZATIONINFO_CACHE_KEY = "AuthorizationInfo-cache-";
    public static final String AUTHENTICATIONINFO_CACHE_KEY = "AuthenticationInfo-cache-";

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = null;
        authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roles = this.roleService.findRoles(username);
        Set<String> roleSet = new HashSet<>();
        List<Integer> roleIdList = new ArrayList<>();
        for (Role role : roles) {
            roleSet.add(role.getName());
            roleIdList.add(role.getId());
        }
        authorizationInfo.setRoles(roleSet);
        if(!CollectionUtils.isEmpty(roleIdList)){
        	List<Function> functionList = this.roleService
                    .findPermissions(roleIdList);
                Set<String> functionSet = new HashSet<>();
                for (Function function : functionList) {
                    if (StringUtils.isNotEmpty(function.getPath())) {
                        functionSet.add(function.getPath());
                    }
                }
                authorizationInfo.setStringPermissions(functionSet);
        }
        return authorizationInfo;
    }

    /**
     * 获取身份认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = null;
        try {
            user = this.userService.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        //如果身份认证验证成功，返回一个AuthenticationInfo实现
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
            user.getUsername(), user.getPassword(), "userrealm");

        if (this.getCredentialsMatcher().doCredentialsMatch(token, info)) {
            SecurityUtils.getSubject().getSession()
                .setAttribute(Constants.SESSION_USER, user);
            return info;
        } else {
            throw new AuthenticationException(
                "username or password incorrect !");
        }
    }
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        if(principals!=null){
            String cacheString = principals.toString();
            return AUTHORIZATIONINFO_CACHE_KEY + cacheString;
        }
        return principals;
    }
    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        if(token!=null){
            return AUTHENTICATIONINFO_CACHE_KEY + token.getPrincipal();
        }
        return token;
    }
}
