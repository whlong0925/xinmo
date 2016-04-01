package com.xinmo.dao;

import java.util.List;

import com.xinmo.entity.Function;
import com.xinmo.service.BaseMapper;

public interface FunctionMapper extends BaseMapper<Function>{

    List<Function> findByType(int type);

}