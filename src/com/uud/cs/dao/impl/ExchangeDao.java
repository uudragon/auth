package com.uud.cs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IExchangeDao;
import com.uud.cs.entity.ExchageGoodsForm;

@Repository("exchangeDao")
public class ExchangeDao extends SqlMapClientDaoSupport implements IExchangeDao {

	@Autowired
	public ExchangeDao(@Qualifier("sqlMapClient") SqlMapClient sqlMapClient){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public void save(Map<String, Object> map) {
		this.getSqlMapClientTemplate().insert( "exchange.save", map );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExchageGoodsForm> findByPage(Map<String, Object> map,
			Integer pageSize, Integer pageNo) {
		return this.getSqlMapClientTemplate().queryForList( "exchange.findByPage", map
				, ( pageNo - 1 ) * pageSize , pageSize );
	}

	@Override
	public Integer countByPage(Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "exchange.countByPage", map );
	}

	@Override
	public void updateStatus(Map<String, Object> map) {
		this.getSqlMapClientTemplate().update( "exchange.updateStauts", map );
	}

}
