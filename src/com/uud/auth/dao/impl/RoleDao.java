package com.uud.auth.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.auth.dao.IRoleDao;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;
import com.uud.auth.entity.Role;

@Repository("roleDao")
public class RoleDao extends SqlMapClientDaoSupport implements IRoleDao {
	
	@Autowired
	public RoleDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ) {
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Role role) {
		return (Long) this.getSqlMapClientTemplate().insert( "role.save",role );
	}

	@Override
	public int update(Role role) {
		return this.getSqlMapClientTemplate().update("role.update", role);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> select( Map<String,Object> map ) {
		return this.getSqlMapClientTemplate().queryForList( "role.findByParams", map );
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "role.countByParams", map );
	}

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return (Role) this.getSqlMapClientTemplate().queryForObject( "role.findById", id );
	}

	@Override
	public void addResource(List<ResourceRole> resource) {
		this.getSqlMapClientTemplate().insert("role.addResources", resource);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceRole> findRolePermissions(){
		return this.getSqlMapClientTemplate().queryForList("role.findRolePermission");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findByParams( Map<String, Object> map, int pageSize, int pageNo ) {
		return this.getSqlMapClientTemplate().queryForList( "role.findByParams", map,
				( pageNo - 1 ) * pageSize, pageSize );
	}

	@Override
	public int delete(Long id) {
		return this.getSqlMapClientTemplate().delete("role.deleteById", id);
	}
	@Override
	public int deleteResourceLink(Long id) {
		return this.getSqlMapClientTemplate().delete("role.deleteResourceLink", id);
	}
	@Override
	public int deleteGroupLink(Long id) {
		return this.getSqlMapClientTemplate().delete("role.deleteGroupLink", id);
	}
	@Override
	public int deleteUserLink(Long id) {
		return this.getSqlMapClientTemplate().delete("role.deleteUserLink", id);
	}
	@Override
	public int deleteUserGroupLink(Long id) {
		return this.getSqlMapClientTemplate().delete("role.deleteUserGroupLink", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		return this.getSqlMapClientTemplate().queryForList( "role.findAll" );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findResources(Long roleId) {
		return this.getSqlMapClientTemplate().queryForList( "role.findResource", roleId );
	}

	@Override
	public void removeResource(Long roleId) {
		this.getSqlMapClientTemplate().delete( "role.removeResource", roleId );
	}
}
