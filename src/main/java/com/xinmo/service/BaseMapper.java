package com.xinmo.service;

import java.util.List;

public interface BaseMapper<T> {
    int insert(T t);

    int updateById(T t);

    int deleteById(Long id);

    T findById(Long id);

    List<T> findAll();
}
