package com.xinmo.service;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	
    int insert(T t);

    int updateById(T t);

    int deleteById(Long id);

    T findById(Long id);

    List<T> findAll();
    
    List<T> findByPage(Map<String,Object> paramMap);
    
    Long getTotalCount(Map<String,Object> paramMap);
}
