package com.xinmo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmo.entity.Function;
import com.xinmo.entity.Role;
import com.xinmo.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list")
    public String list(Model model) throws Exception {
        List<Role> roleList = this.roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "role/list";
    }
    @RequestMapping(value = "/tree")
    public String tree(Model model) throws Exception {
        Map<String, Integer> pMap = new HashMap<>();
        pMap.put("p_type", 1);
        pMap.put("p_role_id", null);
        List<Function> funs =  this.roleService.findTree(pMap);
        model.addAttribute("sys_module", funs);
        return "role/tree";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showRole(@PathVariable("id") Long id, Model model)
            throws Exception {
        if (id > 0) {
            Role role = this.roleService.findById(id);
            model.addAttribute("role", role);
        }
        return "role/add";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("role") Role role) throws Exception {
        this.roleService.updateRole(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) throws Exception {
        this.roleService.deleteRole(id);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("role") Role role) throws Exception {
        this.roleService.insertRole(role);
        return "redirect:/role/list";
    }
}
