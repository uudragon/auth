package com.uud.cs.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.Order;

public interface IOrderService {
	
	public Page<Order> findAudit( Map<String,Object> map, Integer pageSize, Integer pageNo );
	
	public Order findById( Long id );
	
	public int updateWorkFolw( Short workFlow, Long id );
	
	public Long save( Map<String,Object> map ) throws Exception;
	
	public Page<Order> findConsultation(Map<String, Object> map,
			Integer pageSize, Integer pageNo);

	public int updateAudit(Short status, Long id);

	public Order findByNo(String orderNo);

	public Order findByPhone(String phone);

	public int updateDetailStatus(long id, int status);

	public String updateOrderSplit(Long id, String updater);

	public String getOrderSplit(Long id, String updater);

	public int update(Map<String, Object> map);
	
	public List<Order> findByDate( Date startDate, Date endDate, Integer pageSize, Integer pageNo );

	public Integer countByDate(Date startDate, Date endDate);

}
