package com.xinmo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmo.entity.Page;
import com.xinmo.entity.User;
import com.xinmo.service.UserService;
import com.xinmo.util.SecurityUtil;


@Controller
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/list")
    public String list(Model model,HttpServletRequest request)  throws Exception{
    	Page<User> pager = new Page<User>(request);
        this.userService.findByPage(pager);
        model.addAttribute("pager", pager);
        return "user/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Long id, Model model)  throws Exception{
        if(id>0){
            User user = this.userService.findById(id);
            model.addAttribute("user", user);
        }
        return "user/add";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") User user)  throws Exception{
        user.setPassword(SecurityUtil.md5(user.getPassword()));
        this.userService.updateUser(user);
        return "redirect:/user/list";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id)  throws Exception{
        this.userService.deleteUser(id);
        return "redirect:/user/list";
    }
    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("user") User user)  throws Exception{
        user.setPassword(SecurityUtil.md5(user.getPassword()));
        this.userService.insertUser(user);
        return "redirect:/user/list";
    }
}
