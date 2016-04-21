package com.xinmo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xinmo.entity.Page;
import com.xinmo.entity.Role;
import com.xinmo.entity.RoleUser;
import com.xinmo.entity.User;
import com.xinmo.service.UserService;
import com.xinmo.util.SecurityUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list")
	public String list(Model model, HttpServletRequest request)
			throws Exception {
		Page<User> pager = new Page<User>(request);
		this.userService.findByPage(pager);
		model.addAttribute("pager", pager);
		return "user/list";
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showUser(Model model) throws Exception {
		return "user/add";
	}
	@RequestMapping(value = "/{userId}/role", method = RequestMethod.GET)
	public String showUser(@PathVariable("userId") long userId,Model model) throws Exception {
		List<Role> roleList = this.userService.findRoleByUserId(userId);
		User user = this.userService.findById(userId);
		model.addAttribute("roleList", roleList);
		model.addAttribute("user", user);
		return "user/role";
	}
	@RequestMapping(value = "/role/add", method = RequestMethod.POST)
	public String addRoleUser(@RequestParam("userId") long userId,@RequestParam("roleIds") String roleIds,Model model) throws Exception {
		if(StringUtils.isNotEmpty(roleIds)){
			List<RoleUser> roleUserList = new ArrayList<RoleUser>();
			for(String str : roleIds.split(",")){
				if(StringUtils.isNotEmpty(str)){
					roleUserList.add(new RoleUser(userId,Integer.parseInt(str)));
				}
			}
			if(roleUserList.size()>0){
				this.userService.insertRoleUser(roleUserList);
			}
		}
		return "redirect:/user/list";
	}
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") long id, Model model)
			throws Exception {
		User user = this.userService.findById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("user") User user) throws Exception {
		user.setPassword(SecurityUtil.md5(user.getPassword()));
		this.userService.updateUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) throws Exception {
		this.userService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/add")
	public String add(@ModelAttribute("user") User user) throws Exception {
		user.setPassword(SecurityUtil.md5(user.getPassword()));
		this.userService.insertUser(user);
		return "redirect:/user/list";
	}
}
