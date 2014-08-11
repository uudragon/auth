package com.uud.auth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.auth.dao.IResourceDao;
import com.uud.auth.entity.Resource;

@Repository("resourceDao")
public class ResourceDao extends SqlMapClientDaoSupport implements IResourceDao {

	@Autowired
	public ResourceDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ) {
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Resource resource) {
		
		return (Long) this.getSqlMapClientTemplate().insert("resource.save", resource);
	}

	@Override
	public int update(Resource resource) {
		return this.getSqlMapClientTemplate().update("resource.update", resource);
	}

	@Override
	public Resource findById(Long id) {
		return (Resource) this.getSqlMapClientTemplate().queryForObject( "resource.findById", id );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findByParams( Resource r ) {
		return this.getSqlMapClientTemplate().queryForList( "resource.findByParams", r );
	}

	@Override
	public int countByParams( Resource r ) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "resource.countByParams", r );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findAll(){
		return this.getSqlMapClientTemplate().queryForList( "resource.findAll" );
	}

	@Override
	public int deleteById(Long id) {
		return this.getSqlMapClientTemplate().delete( "resource.deleteById",id );
	}

	@Override
	public int deleteRoleResource(Long resourceId) {
		return this.getSqlMapClientTemplate().delete( "resource.deleteRoleResource", resourceId );
	}

	@Override
	public int deleteRoleGroupResource(Long resourceId) {
		return this.getSqlMapClientTemplate().delete( "resource.deleteRoleGroupResource", resourceId );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findByParent( Long resourceId ){
		return this.getSqlMapClientTemplate().queryForList( "resource.selectByParent", resourceId );
	}
}
