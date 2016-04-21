package com.xinmo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinmo.entity.Function;
import com.xinmo.service.FunctionService;

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

    /**
     * 构建treetable需要的顺序，后台返回的数据顺序必须按tree的显示方式输出
     * @param functionList
     * @return
     */
    private List<Function> buildTreeTableList(List<Function> functionList) {
    	List<Function> newFunctionList = new ArrayList<Function>();
    	if(!CollectionUtils.isEmpty(functionList)){
    		Map<String,List<Function>> map = new LinkedHashMap<>();
    		for(Function function : functionList){
    			int parentId = function.getParentId() == null ? 0:function.getParentId();
    			String parentKey = ""+parentId;
    			if(map.containsKey(parentKey)){
    				List<Function> list = map.get(parentKey);
    				list.add(function);
    			}else{
    				List<Function> list = new ArrayList<Function>();
    				list.add(function);
    				map.put(parentKey, list);
    			}
    		}
    		//获取根节点
    		List<Function> rootList = map.get("0");
    		for(Function root:rootList){
    			newFunctionList.add(root);
    			getChildRoot(newFunctionList,map,root);
    		}
    	}
		return newFunctionList;
	}

	private void getChildRoot(List<Function> newList, Map<String,List<Function>> map, Function root) {
		List<Function> childList = map.get(""+root.getId());
		if(CollectionUtils.isEmpty(childList)){
			return;
		}
		for(Function child : childList){
			newList.add(child);
			getChildRoot(newList, map, child);
		}
		
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showFunction(Model model) throws Exception {
        return "module/add";
    }
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editFunction(@PathVariable("id") int id, Model model) throws Exception {
		Function function = this.functionService.findById(id);
		model.addAttribute("module", function);
		return "module/add";
	}

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("function") Function function) throws Exception {
        this.functionService.updateFunction(function);
        return "redirect:/module/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) throws Exception {
        this.functionService.deleteFunction(id);
        return "redirect:/module/list";
    }

    @RequestMapping(value = "/add")
    public String add(@ModelAttribute("function") Function function) throws Exception {
        this.functionService.insertFunction(function);
        return "redirect:/module/list";
    }
}
