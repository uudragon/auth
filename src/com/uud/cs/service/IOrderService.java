package com.uud.cs.service;

import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.Order;

public interface IOrderService {
	
	public Page<Order> findAudit( Map<String,Object> map, Integer pageSize, Integer pageNo );
	
	public Order findById( Long id );
	
	public int updateStatus( Short status, Long id );
	
	public Long save( Map<String,Object> map );
}
