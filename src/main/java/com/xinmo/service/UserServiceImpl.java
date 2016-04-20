package com.xinmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmo.dao.UserMapper;
import com.xinmo.entity.Function;
import com.xinmo.entity.Page;
import com.xinmo.entity.Role;
import com.xinmo.entity.RoleUser;
import com.xinmo.entity.User;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RedisService redisService;

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
    public User findByUsername(String username) throws Exception {
        return this.userMapper.findByUsername(username);
    }
 
	@Override
	public Page<User> findByPage(Page<User> pager) throws Exception {
		List<User> userList = this.userMapper.findByPage(pager.getParam());
		pager.setResult(userList);
		long totalCount = this.userMapper.getTotalCount(pager.getParam());
		pager.setTotalCount(totalCount);
		return pager;
	}

	@Override
	public List<Role> findRoleByUserId(long userId) throws Exception {
		List<Role> roleList = this.userMapper.findRoleByUserId(userId);
		return roleList;
	}

	@Override
	public void insertRoleUser(List<RoleUser> roleUserList) throws Exception {
		this.userMapper.insertRoleUser(roleUserList);
		
	}

	@Override
	public List<Function> findMenusByUserId(Long userId) throws Exception {
		return this.userMapper.findMenusByUserId(userId);
	}
}
