package com.uud.cs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.ICommunicationDao;

@Repository("communicationDao")
public class CommunicationDao extends SqlMapClientDaoSupport implements ICommunicationDao {

	@Autowired
	public CommunicationDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "communication.save", map );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findCommunication(String code) {
		return this.getSqlMapClientTemplate().queryForList( "communication.findCommunication", code );
	}

}
