package com.uud.cs.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.uud.cs.entity.Order;

public interface IOrderDao {
	
	public Long save( Map<String,Object> map );

	public List<Order> findAudit( Map<String,Object> map, Integer pageSize, Integer pageNo );
	
	public Integer countAudit( Map<String,Object> map );
	
	public Order findById( Long id );
	
	public Long saveDetail( Map<String,Object> map );
	
	public List<Order> findConsultation( Map<String,Object> map, Integer pageSize, Integer pageNo );

	public Integer countConsultation(Map<String, Object> map);

	public int update(Map<String, Object> map);

	public Integer updateWorkFlow(Map<String, Object> map);

	public Order findByNo(String order_no);

	public Order findByPhone(String phone);
	
	public int updateDetailStatus(long id, int status);
	
	public List<Order> findByDate( Date startDate, Date endDate, Integer pageSize, Integer pageNo );

	public Integer countByDate(Date startDate, Date endDate);
}
