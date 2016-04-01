package com.xinmo.dao;

import com.xinmo.entity.User;
import com.xinmo.service.BaseMapper;

public interface UserMapper extends BaseMapper<User>{
    User findByUsercode(String usercode) throws Exception;
}