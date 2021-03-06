package com.uud.cs.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	public int update( Map<String,Object> map ){
		return this.getSqlMapClientTemplate().update( "order.update", map );
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
	
	@Override
	public Integer updateWorkFlow( Map<String,Object> map ){
		return this.getSqlMapClientTemplate().update( "order.updateWorkFlow", map );
	}
	
	@Override
	public Order findByNo( String order_no ){
		return (Order) this.getSqlMapClientTemplate().queryForObject( "order.findByNo", order_no );
	}
	
	@Override
	public Order findByPhone( String phone ){
		return (Order) this.getSqlMapClientTemplate().queryForObject( "order.findByPhone", phone );
	}

	@Override
	public int updateDetailStatus(long id, int status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "id", id );
		map.put( "status", status );
		return this.getSqlMapClientTemplate().update( "order.updateDetailStatus", map );
	}

	@SuppressWarnings("unchecked")
	public List<Order> findByDate(Date startDate, Date endDate, Integer pageSize, Integer pageNo ) {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("endDate", sdf.format( endDate ) );
		map.put("startDate", sdf.format( startDate ));
		map.put("start", ( pageNo - 1 ) * pageSize );
		map.put("pageSize", pageSize );
		return this.getSqlMapClientTemplate().queryForList( "order.findByDate", map );
	}
	
	@Override
	public Integer countByDate(Date startDate, Date endDate ) {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		map.put("endDate", sdf.format( endDate ) );
		map.put("startDate", sdf.format( startDate ));
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "order.countByDate", map );
	}
}
