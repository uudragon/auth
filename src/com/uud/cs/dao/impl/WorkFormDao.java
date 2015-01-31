package com.uud.cs.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IWorkFormDao;
import com.uud.cs.entity.WorkForm;

@Repository("workFormDao")
public class WorkFormDao extends SqlMapClientDaoSupport implements IWorkFormDao {

	@Autowired
	public WorkFormDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert("workform.save", map);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<WorkForm> findByPage( Map<String,Object> map, Integer pageSize, Integer pageNo ){
		return this.getSqlMapClientTemplate().queryForList("workform.findByPage", map,
											( pageNo - 1 ) * pageSize , pageSize );
	}
	
	@Override
	public Integer countByPage( Map<String,Object> map ){
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "workform.countByPage", map );
	}

	@Override
	public Integer updateStatus( Integer status, Long id ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("id", id);
		return this.getSqlMapClientTemplate().update( "workform.updateStatus", map );
	}
}
