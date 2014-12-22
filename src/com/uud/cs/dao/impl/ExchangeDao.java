package com.uud.cs.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IExchangeDao;

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

}
