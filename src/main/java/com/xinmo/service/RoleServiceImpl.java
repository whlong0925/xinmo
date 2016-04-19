package com.xinmo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xinmo.dao.RoleMapper;
import com.xinmo.entity.Function;
import com.xinmo.entity.Role;
import com.xinmo.entity.RoleFunction;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertRole(Role role) throws Exception {
    	int rows = this.roleMapper.insertRole(role);
    	insertRoleFunction(role);
        return rows>0?role.getId():0;
    }

    @Override
    public int updateRole(Role role) throws Exception {
    	this.roleMapper.deleteRoleFunctionByRoleId(role.getId());
    	insertRoleFunction(role);
        return this.roleMapper.updateById(role);
    }

    @Override
    public int deleteRole(int roleId) throws Exception {
        return this.roleMapper.deleteById((long)roleId);
    }

    @Override
    public Role findById(int roleId) throws Exception {
        return this.roleMapper.findById((long)roleId);
    }

    @Override
    public List<Role> findAll() throws Exception {
        return this.roleMapper.findAll();
    }

    @Override
    public List<Function> findTree(Map<String, Integer> pMap) {
        return this.roleMapper.findTree(pMap);
    }

    @Override
    public List<Role> findRoles(String username) {
        List<Role> listRoles = this.roleMapper.findRolesByUserName(username);
        return listRoles;
    }

    @Override
    public List<Function> findPermissions(List<Integer> roleIds) {
        return this.roleMapper.findFunctionsByRoleId(roleIds);
    }

    private void insertRoleFunction(Role role){
    	if(StringUtils.isNotEmpty(role.getFunctionIds())){
    		List<RoleFunction> roleFunctionList = new ArrayList<RoleFunction>();
    		for(String functionId:role.getFunctionIds().split(",")){
    			RoleFunction rf = new RoleFunction(role.getId(),Integer.parseInt(functionId));
    			roleFunctionList.add(rf);
    		}
    		if(!CollectionUtils.isEmpty(roleFunctionList)){
    			this.roleMapper.insertRoleFunction(roleFunctionList);
    		}
    	}
    	
    }
}
