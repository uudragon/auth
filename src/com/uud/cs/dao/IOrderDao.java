package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.Order;

public interface IOrderDao {
	
	public Long save( Map<String,Object> map );

	public List<Order> findAudit( Map<String,Object> map, Integer pageSize, Integer pageNo );
	
	public Integer countAudit( Map<String,Object> map );
	
	public Order findById( Long id );

	public int update(Short stats, Long id);
	
	public Long saveDetail( Map<String,Object> map );
}
