package com.xinmo.dao;

import java.util.List;
import java.util.Map;

import com.xinmo.entity.Function;
import com.xinmo.entity.Role;
import com.xinmo.entity.RoleFunction;
import com.xinmo.service.BaseMapper;

public interface RoleMapper extends BaseMapper<Role>{
    
    int insertRole(Role role) throws Exception;

    List<Map<String, Object>> tree(Map<String, Integer> paramMap) throws Exception;

    List<Function> findTree(Map<String, Integer> pMap);

    List<Role> findRolesByUserName(String userName);

    List<Function> findFunctionsByRoleId(List<Integer> roleIds);

	void insertRoleFunction(List<RoleFunction> roleFunctionList);

	void deleteRoleFunctionByRoleId(Integer id);
}