package com.uud.cs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IReturnDao;
import com.uud.cs.entity.ReturnGoodsForm;

@Repository("returnDao")
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

	@Override
	public Integer countByParams( Map<String,Object> map ){
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "return.countByParams", map );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReturnGoodsForm> findByPage( Map<String,Object> map, Integer pageNo,Integer pageSize ){
		return this.getSqlMapClientTemplate().queryForList( "return.findByParams", 
				map, ( pageNo - 1 ) * pageSize , pageSize);
	}
	
	@Override
	public void updateStatus( Map<String,Object> map ){
		this.getSqlMapClientTemplate().update( "return.update", map );
	}
}
