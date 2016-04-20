package com.xinmo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.code.kaptcha.Constants;
import com.xinmo.entity.Function;
import com.xinmo.entity.User;
import com.xinmo.service.UserService;

@Controller
public class IndexController {
    static Logger logger = LogManager.getLogger(IndexController.class);
    @Autowired
	private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("username") String username,
            @RequestParam("password") String password,@RequestParam("kaptcha") String kaptcha,HttpServletRequest request) {
    	
        // 就是代表当前的用户。
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        String sessionKaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isEmpty(sessionKaptcha) || !kaptcha.equalsIgnoreCase(sessionKaptcha)) {
        	model.addAttribute("errorMessage", "验证码错误");
        	return "login";
        }
        
        //获取基于用户名和密码的令牌 
        UsernamePasswordToken token = new UsernamePasswordToken(username,
            password);
        token.setRememberMe(true);
        try {
            // 回调doGetAuthenticationInfo，进行认证
            currentUser.login(token);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
        //验证是否通过 
        if (currentUser.isAuthenticated()) {
            return "redirect:index";
        } else {
            model.addAttribute("errorMessage", "用户名或者密码错误！");
            return "login";
        }
    }

    @RequestMapping(value = "/unauthz")
    public String unauth() {
        return "unauthz";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
    	Object objUser = SecurityUtils.getSubject().getSession().getAttribute(com.xinmo.util.Constants.SESSION_USER);
    	if(objUser!=null){
    		User sessionUser = (User)objUser;
    		List<Function> functionList = this.userService.findMenusByUserId(sessionUser.getId());
    		Map<String,List<Function>> menuMap = new LinkedHashMap<>();
    		Map<String,String> moduleMap = new HashMap<>();
    		for(Function f : functionList){
    			if(f.getFunctionType() == 0){
    				menuMap.put(f.getId()+"", new ArrayList<Function>());
    				moduleMap.put(f.getId()+"",f.getName());
    			}else{
    				if(menuMap.containsKey(f.getParentId()+"")){
    					List<Function> list = menuMap.get(f.getParentId()+"");
    					list.add(f);
    				}
    			}
    			model.addAttribute("moduleMap", moduleMap);
    			model.addAttribute("menuMap", menuMap);
    		}
    	}
        return "index";
    }
}
