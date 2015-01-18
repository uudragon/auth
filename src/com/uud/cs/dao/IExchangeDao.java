package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.ExchageGoodsForm;

public interface IExchangeDao {
	public void save( Map<String,Object> map );
	
	public List<ExchageGoodsForm> findByPage( Map<String,Object> map, Integer pageSize, Integer pageNo );
	
	public Integer countByPage( Map<String,Object> map );
	
	public void updateStatus( Map<String,Object> map );
}
