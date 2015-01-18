package com.uud.cs.service;

import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.ExchageGoodsForm;

public interface IExchangeService {
	
	public void save( Map<String,Object> map );
	
	public Page<ExchageGoodsForm> findByPage(Map<String, Object> map, Integer pageSize, Integer pageNo);

	public void updateStatus(Map<String, Object> map);
}
