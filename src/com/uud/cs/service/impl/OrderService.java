package com.uud.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
import com.uud.auth.ws.client.OrderSplitService;
import com.uud.auth.ws.client.ProductService;
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
	
	@Autowired
	@Qualifier("orderSplit")
	private OrderSplitService orderSplit;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
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
	public int updateAudit(Short audit, Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("audit", audit);
		map.put("id", id);
		if( Order.AUDIT_INVALID == audit ){
			map.put("status", Order.STATUS_INVALID );
		}
		if( Order.AUDIT_DONE == audit ){
			map.put( "workflow", Order.WORKFLOW_SPLIT );
		}
		return orderDao.update( map );
	}
	
	@Override
	public Long save(Map<String, Object> map) throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<String,Object> customer = (Map<String, Object>) map.get("customer");
		if( customer != null ){
			Customer c = customerDao.findByCode( (String)customer.get("code") );
			if( c != null ){
				customerDao.update( customer );
			} else {
				customer.put( "code", UUID.randomUUID().toString() );
				customerDao.save( customer );
			}
			
			/*List<Map<String,Object>> details = productService
										.getProductByOrderType( (String)map.get("order_type"));
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> details = (List<Map<String, Object>>) map.get( "details" );
			if( details != null ){
				for( Map<String,Object> detail : details ){
					detail.put("orders_no", map.get("order_no") );
					orderDao.saveDetail( detail );
				}
			}*/
			map.put("customer_code", customer.get("code") );
			return orderDao.save( map );
		}
		return null;
	}
	
	@Override
	public Page<Order> findConsultation(Map<String, Object> map,
			Integer pageSize, Integer pageNo) {
		
		Page<Order> page = new Page<Order>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		Integer count = orderDao.countConsultation( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize == 0 ? count / pageSize : count / pageSize + 1 );
		List<Order> list = orderDao.findConsultation(map, pageSize, pageNo);
		page.setRecords( list );
		return page;
	}
	
	@Override
	public int updateWorkFolw(Short workFlow, Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("workflow", workFlow);
		map.put("id", id);
		return orderDao.update(map);
	}
	
	@Override
	public String updateOrderSplit( Long id ) {
		Order order = orderDao.findById(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("workflow", 4);
		map.put("preflow", 2);
		map.put("id", id);
		if( orderDao.updateWorkFlow(map) == 1 ){
			return orderSplit.getSplitDetails( order );
		}
		return "[]";
	}
	
	@Override
	public int updateDetailStatus( long id, int status ){
		return orderDao.updateDetailStatus( id, status );
	}
	
	@Override
	public Order findByNo( String orderNo ){
		return orderDao.findByNo( orderNo );
	}
	
	@Override
	public Order findByPhone( String phone ){
		return orderDao.findByPhone( phone );
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
