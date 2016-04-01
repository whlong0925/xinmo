package com.xinmo.service;

import java.util.List;

import com.xinmo.entity.Function;

public interface FunctionService {
    public List<Function> findByType(int type) throws Exception;

    public int insertFunction(Function function) throws Exception;

    public int updateFunction(Function function) throws Exception;

    public int deleteFunction(Long functionId) throws Exception;

    public Function findById(Long functionId) throws Exception;
}
