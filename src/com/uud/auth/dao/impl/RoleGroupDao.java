package com.uud.auth.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.auth.dao.IRoleGroupDao;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRoleGroup;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.RoleGroup;

@Repository("roleGroupDao")
public class RoleGroupDao extends SqlMapClientDaoSupport implements IRoleGroupDao {

	@Autowired
	public RoleGroupDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ) {
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(RoleGroup rg) {
		return (Long) this.getSqlMapClientTemplate().insert( "rg.save", rg );
	}

	@Override
	public RoleGroup findById(Long id) {
		return (RoleGroup) this.getSqlMapClientTemplate().queryForObject( "rg.findById", id );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleGroup> findByParams(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList( "rg.findByParams", map );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleGroup> findByPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		return this.getSqlMapClientTemplate().queryForList( "rg.findByParams", map,
				( pageNo - 1 ) * pageSize , pageSize );
	}
	
	@Override
	public int update(RoleGroup rg) {
		return this.getSqlMapClientTemplate().update( "rg.update", rg );
	}

	@Override
	public int countByParams(Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "rg.countByParams", map );
	}

	@Override
	public void addRole(List<Role> roles) {

		this.getSqlMapClientTemplate().insert( "rg.addRoles", roles);
		
	}

	@Override
	public void addResource(List<ResourceRoleGroup> rrg) {
		this.getSqlMapClientTemplate().insert( "rg.addResources", rrg );
		
	}

	@Override
	public int delete(Long id) {
		return this.getSqlMapClientTemplate().delete( "rg.deleteById", id );
	}

	@Override
	public int deleteRole(Long id) {
		return this.getSqlMapClientTemplate().delete( "rg.deleteRole", id );
	}

	@Override
	public int deleteResource(Long id) {
		return this.getSqlMapClientTemplate().delete( "rg.deleteResource", id );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleGroup> findAll() {
		return this.getSqlMapClientTemplate().queryForList( "rg.findAll" );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findResources(Long roleId) {
		return this.getSqlMapClientTemplate().queryForList( "rg.findResource", roleId );
	}


}
