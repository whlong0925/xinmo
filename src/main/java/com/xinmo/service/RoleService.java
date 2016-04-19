package com.xinmo.service;

import java.util.List;
import java.util.Map;

import com.xinmo.entity.Function;
import com.xinmo.entity.Role;

public interface RoleService {
    
    public int insertRole(Role role) throws Exception;

    public int updateRole(Role role) throws Exception;

    public int deleteRole(int roleId) throws Exception;

    public Role findById(int roleId) throws Exception;

    public List<Role> findAll() throws Exception;

    public List<Function> findTree(Map<String, Integer> pMap);

    public List<Role> findRoles(String usercode);

    public List<Function> findPermissions(List<Integer> roleIds);
}
