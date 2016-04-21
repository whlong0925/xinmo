package com.xinmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xinmo.entity.Function;
import com.xinmo.entity.Role;
import com.xinmo.service.FunctionService;
import com.xinmo.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/list")
    public String list(Model model) throws Exception {
        List<Role> roleList = this.roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "role/list";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showRole(Model model) throws Exception {
        List<Function> functionList = this.functionService.findByRole(0);
    	model.addAttribute("functionList", functionList);
        return "role/add";
    }
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editRole(@PathVariable("id") int id, Model model) throws Exception {
		Role role = this.roleService.findById(id);
		model.addAttribute("role", role);
    	List<Function> functionList = this.functionService.findByRole(id);
    	model.addAttribute("functionList", functionList);
    	return "role/add";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("role") Role role) throws Exception {
        this.roleService.updateRole(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) throws Exception {
        this.roleService.deleteRole(id);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("role") Role role, RedirectAttributes redirectAttributes) throws Exception {
        this.roleService.insertRole(role);
        redirectAttributes.addFlashAttribute("message", "角色添加成功");
        return "redirect:/role/list";
    }
}
