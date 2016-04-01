package com.xinmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmo.entity.Function;
import com.xinmo.service.FunctionService;
import com.xinmo.util.Constants;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/list")
    public String list(Model model) throws Exception {
        List<Function> functionList = this.functionService.findByType(Constants.MODULE_TYPE);
        model.addAttribute("moduleList", functionList);
        return "module/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showFunction(@PathVariable("id") Long id, Model model)
            throws Exception {
        if (id > 0) {
            Function function = this.functionService.findById(id);
            model.addAttribute("module", function);
        }
        return "module/add";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("function") Function function) throws Exception {
        function.setFunctionType(Constants.MODULE_TYPE);
        this.functionService.updateFunction(function);
        return "redirect:/module/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) throws Exception {
        this.functionService.deleteFunction(id);
        return "redirect:/module/list";
    }

    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("function") Function function) throws Exception {
        function.setFunctionType(Constants.MODULE_TYPE);
        this.functionService.insertFunction(function);
        return "redirect:/module/list";
    }
}
