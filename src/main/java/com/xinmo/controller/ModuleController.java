package com.xinmo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.xinmo.service.FunctionService;
import com.xinmo.util.Constants;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/list")
    public String list(Model model) throws Exception {
        List<Function> functionList = this.functionService.findAll();
        List<Function> moduleList = buildTreeTableList(functionList);
        model.addAttribute("moduleList", moduleList);
        return "module/list";
    }

    private List<Function> buildTreeTableList(List<Function> functionList) {
    	Map<String,List<Function>> map = new LinkedHashMap<>();
		for(Function function : functionList){
			String id = ""+function.getId();
			int parentId = function.getParentId();
			String parentKey = ""+parentId;
			if(map.containsKey(id)){
				List<Function> list = map.get(id);
				list.add(function);
			}else{
				List<Function> list = new ArrayList<Function>();
				list.add(function);
				map.put(id, list);
			}
		}
		return functionList;
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
