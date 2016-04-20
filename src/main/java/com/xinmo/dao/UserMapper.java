package com.xinmo.dao;

import java.util.List;

import com.xinmo.entity.Function;
import com.xinmo.entity.Role;
import com.xinmo.entity.RoleUser;
import com.xinmo.entity.User;
import com.xinmo.service.BaseMapper;

public interface UserMapper extends BaseMapper<User>{
	
    User findByUsername(String usercode) throws Exception;

	List<Role> findRoleByUserId(long userId) throws Exception;

	void insertRoleUser(List<RoleUser> roleUserList) throws Exception;

	List<Function> findMenusByUserId(Long userId) throws Exception;
}