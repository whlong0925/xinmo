package com.xinmo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    static Logger logger = LogManager.getLogger(IndexController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("username") String username,
            @RequestParam("password") String password) {
        // 就是代表当前的用户。
        Subject currentUser = SecurityUtils.getSubject();
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
    public String index() throws Exception {
        return "index";
    }
}
