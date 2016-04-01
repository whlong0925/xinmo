package com.xinmo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmo.dao.RoleMapper;
import com.xinmo.entity.Function;
import com.xinmo.entity.Role;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertRole(Role role) throws Exception {
        return this.roleMapper.insertRole(role);
    }

    @Override
    public int updateRole(Role role) throws Exception {
        return this.roleMapper.updateById(role);
    }

    @Override
    public int deleteRole(Long roleId) throws Exception {
        return this.roleMapper.deleteById(roleId);
    }

    @Override
    public Role findById(Long roleId) throws Exception {
        return this.roleMapper.findById(roleId);
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
    public List<Role> findRoles(String usercode) {
        List<Role> listRoles = this.roleMapper.findRolesByUserCode(usercode);
        return listRoles;
    }

    @Override
    public List<Function> findPermissions(List<Integer> roleIds) {
        return this.roleMapper.findFunctionsByRoleId(roleIds);
    }

}
