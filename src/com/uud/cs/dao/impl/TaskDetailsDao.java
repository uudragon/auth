package com.uud.cs.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.ITaskDetailsDao;

@Repository("taskDetailsDao")
public class TaskDetailsDao extends SqlMapClientDaoSupport implements ITaskDetailsDao {

	@Autowired
	public TaskDetailsDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "task.save", map );
	}

}
