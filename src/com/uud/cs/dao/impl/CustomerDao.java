package com.uud.cs.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.ICustomerDao;
import com.uud.cs.entity.Customer;

@Repository("customerDao")
public class CustomerDao extends SqlMapClientDaoSupport implements ICustomerDao {

	@Autowired
	public CustomerDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "consumer.save", map );
	}

	@Override
	public Customer findByCode( String code ){
		return (Customer) this.getSqlMapClientTemplate().queryForObject( "consumer.findByCode", code );
	}
	
	@Override
	public int update(Map<String, Object> map){
		return this.getSqlMapClientTemplate().update( "consumer.update", map );
	}
}
