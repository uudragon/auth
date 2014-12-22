package com.uud.cs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IKnowledgeBaseDao;
import com.uud.cs.entity.KnowledgeBase;

@Repository("knowledgeBaseDao")
public class KnowledgeBaseDao extends SqlMapClientDaoSupport implements IKnowledgeBaseDao{

	@Autowired
	public KnowledgeBaseDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient( sqlMapClient );
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "knowledgebase.save", map );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KnowledgeBase> findByParams(Map<String, Object> map,Integer pageNo,Integer pageSize) {
		int skipResults = 0;
		if( pageNo > 1){
			skipResults = ( pageNo - 1 ) * pageSize;
		}
		return this.getSqlMapClientTemplate().queryForList( "knowledgebase.findByPage", map, skipResults, pageSize );
	}
	
	@Override
	public Integer countByParams( Map<String,Object> map ){
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "knowledgebase.countByPage", map );
	}

	@Override
	public Integer update(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().update( "knowledgebase.update", map );
	}
	
	@Override
	public Integer updateBN( Long id ) {
		return this.getSqlMapClientTemplate().update( "knowledgebase.updateBN", id );
	}

	@Override
	public KnowledgeBase findById(Long id) {
		return (KnowledgeBase) this.getSqlMapClientTemplate().queryForObject( "knowledgebase.findById", id );
	}

	@Override
	public Integer delete(Long id) {
		return this.getSqlMapClientTemplate().delete( "knowledgebase.delete", id );
	}

}
