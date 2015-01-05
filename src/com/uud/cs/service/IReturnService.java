package com.uud.cs.service;

import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.ReturnGoodsForm;

public interface IReturnService {
	
	public Page<ReturnGoodsForm> findByPage( Map<String, Object> map, Integer pageNo, Integer pageSize );
	
	
	
}
