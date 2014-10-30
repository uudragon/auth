package com.uud.cs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
import com.uud.cs.dao.ICustomerDao;
import com.uud.cs.dao.IOrderDao;
import com.uud.cs.entity.Customer;
import com.uud.cs.entity.Order;
import com.uud.cs.service.IOrderService;

@Service("orderService")
public class OrderService implements IOrderService {

	@Autowired
	@Qualifier("orderDao")
	private IOrderDao orderDao;
	
	@Autowired
	@Qualifier("customerDao")
	private ICustomerDao customerDao;
	
	@Override
	public Page<Order> findAudit( Map<String,Object> map, Integer pageSize, Integer pageNo) {
		Page<Order> page = new Page<Order>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		Integer count = orderDao.countAudit( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize == 0 ? count / pageSize : count / pageSize + 1 );
		List<Order> list = orderDao.findAudit(map,pageSize, pageNo);
		page.setRecords( list );
		return page;
	}

	@Override
	public Order findById(Long id) {
		return orderDao.findById( id );
	}

	@Override
	public int updateStatus(Short status, Long id) {
		return orderDao.update(status, id);
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> customer = (Map<String, Object>) map.get("customer");
		if( customer != null ){
			Customer c = customerDao.findByCode( (String)customer.get("code") );
			if( c != null ){
				customerDao.update( customer );
			} else {
				customerDao.save( customer );
			}
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> details = (List<Map<String, Object>>) map.get( "details" );
			if( details != null ){
				for( Map<String,Object> detail : details ){
					orderDao.saveDetail( detail );
				}
			}
			map.put("customer_code", customer.get("code") );
			return orderDao.save( map );
		}
		return null;
	}
	
	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public ICustomerDao getConsumerDao() {
		return customerDao;
	}

	public void setConsumerDao(ICustomerDao consumerDao) {
		this.customerDao = consumerDao;
	}
	
}
