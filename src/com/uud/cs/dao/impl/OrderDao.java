package com.uud.cs.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IOrderDao;
import com.uud.cs.entity.Order;

@Repository("orderDao")
public class OrderDao extends SqlMapClientDaoSupport implements IOrderDao{
	
	@Autowired
	public OrderDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ) {
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "order.save", map );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAudit( Map<String,Object> map, Integer pageSize, Integer pageNo ){
		int skipResults = 0;
		if( pageNo > 1){
			skipResults = ( pageNo - 1 ) * pageSize;
		}
		return this.getSqlMapClientTemplate().queryForList("order.findAudit", map, skipResults, pageSize);
	}

	@Override
	public Integer countAudit( Map<String,Object> map ) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("order.countAudit", map);
	}

	@Override
	public Order findById(Long id) {
		return (Order) this.getSqlMapClientTemplate().queryForObject("order.findById", id);
	}

	@Override
	public int update( Short stats, Long id ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", stats);
		map.put("id", id);
		return this.getSqlMapClientTemplate().update( "order.updateStatus", map );
	}

	@Override
	public Long saveDetail(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "order.saveDetail", map );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findConsultation(Map<String, Object> map,
			Integer pageSize, Integer pageNo) {
		int skipResults = 0;
		if( pageNo > 1){
			skipResults = ( pageNo - 1 ) * pageSize;
		}
		return this.getSqlMapClientTemplate().queryForList( "order.findConsultation", map,
				skipResults, pageSize);
	}
	
	@Override
	public Integer countConsultation( Map<String,Object> map ){
		return (Integer) this.getSqlMapClientTemplate().queryForObject( 
								"order.countConsultation", map );
	}
}
