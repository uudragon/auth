package com.uud.cs.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IReturnDao;

public class ReturnDao extends SqlMapClientDaoSupport implements IReturnDao {

	@Autowired
	public ReturnDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public void save(Map<String, Object> map) {
		this.getSqlMapClientTemplate().insert( "return.save", map );
		
	}

}
