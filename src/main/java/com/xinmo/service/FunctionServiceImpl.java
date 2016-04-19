package com.xinmo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinmo.dao.FunctionMapper;
import com.xinmo.entity.Function;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public int insertFunction(Function function) throws Exception {
        return this.functionMapper.insert(function);
    }

    @Override
    public int updateFunction(Function function) throws Exception {
        return this.functionMapper.updateById(function);
    }

    @Override
    public int deleteFunction(Long functionId) throws Exception {
        return this.functionMapper.deleteById(functionId);
    }

    @Override
    public Function findById(Long functionId) throws Exception {
        return this.functionMapper.findById(functionId);
    }

    @Override
    public List<Function> findByType(int type) throws Exception {
        return this.functionMapper.findByType(type);
    }

	@Override
	public List<Function> findAll() throws Exception {
		return this.functionMapper.findAll();
	}

	/**
	 * roleId = 0 表示查询所有功能 
	 * >0 表示查询具体角色对应的功能
	 */
	@Override
	public List<Function> findByRole(int roleId) throws Exception {
		if(roleId>0){
			return this.functionMapper.findByRole(roleId);
		}else{
			return this.functionMapper.findAll();
		}
	}

}
