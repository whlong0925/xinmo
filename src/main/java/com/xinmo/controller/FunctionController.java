package com.xinmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmo.entity.Function;
import com.xinmo.service.FunctionService;

@Controller
@RequestMapping("/function")
public class FunctionController {
	@Autowired
	private FunctionService functionService;

	/**
	 * 添加
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showFunction(@PathVariable("id") int id, Model model) throws Exception {
		Function function = this.functionService.findById(id);
		model.addAttribute("parentName", function.getName());
		model.addAttribute("parentId", function.getId());
		int functionType = function.getFunctionType()==0?1:2;
		model.addAttribute("functionType", functionType);
		return "function/add";
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editFunction(@PathVariable("id") int id, Model model)
			throws Exception {
		Function function = this.functionService.findById(id);
		model.addAttribute("function", function);
		Function parentFunction = this.functionService.findById(function.getParentId());
		model.addAttribute("parentName", parentFunction.getName());
		model.addAttribute("parentId", parentFunction.getId());
		model.addAttribute("functionType", function.getFunctionType());
		return "function/add";
	}

}
