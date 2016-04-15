package com.xinmo.service;


import java.util.List;

import com.xinmo.entity.Page;
import com.xinmo.entity.User;

public interface UserService {

    public int insertUser(User user)  throws Exception;

    public int updateUser(User user) throws Exception;

    public int deleteUser(Long userId) throws Exception;

    public void changePassword(Long userId, String newPassword) throws Exception;

    public User findById(Long userId) throws Exception;

    public List<User> findAll() throws Exception;

    public User findByUsername(String usercode) throws Exception;

	public Page<User> findByPage(Page<User> pager) throws Exception;


}
