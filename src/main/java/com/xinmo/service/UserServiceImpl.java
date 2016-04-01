package com.xinmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmo.dao.UserMapper;
import com.xinmo.entity.User;

import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user)  throws Exception{
        return this.userMapper.insert(user);
    }

    @Override
    public int updateUser(User user)  throws Exception{
        return this.userMapper.updateById(user);
    }

    @Override
    public int deleteUser(Long userId)  throws Exception{
        return this.userMapper.deleteById(userId);
    }

    @Override
    public void changePassword(Long userId, String newPassword) throws Exception {
        
    }

    @Override
    public User findById(Long userId)  throws Exception{
        return this.userMapper.findById(userId);
    }

    @Override
    public List<User> findAll()  throws Exception{
        return this.userMapper.findAll();
    }

    @Override
    public User findByUsercode(String username) throws Exception {
        return this.userMapper.findByUsercode(username);
    }

    

}
