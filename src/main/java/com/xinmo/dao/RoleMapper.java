package com.xinmo.dao;

import java.util.List;
import java.util.Map;

import com.xinmo.entity.Function;
import com.xinmo.entity.Role;

public interface RoleMapper {
    
    int insertRole(Role role) throws Exception;

    int deleteById(Long id) throws Exception;

    int updateById(Role role) throws Exception;

    Role findById(Long id) throws Exception;

    List<Role> findAll() throws Exception;

    List<Map<String, Object>> tree(Map<String, Integer> paramMap) throws Exception;

    List<Function> findTree(Map<String, Integer> pMap);

    List<Role> findRolesByUserName(String userName);

    List<Function> findFunctionsByRoleId(List<Integer> roleIds);
}