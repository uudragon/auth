package com.uud.auth.dao;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRoleGroup;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.RoleGroup;

public interface IRoleGroupDao {
	
	public List<Resource> findResources( Long roleId );
	
	public List<RoleGroup> findAll();
	
	public Long save( RoleGroup rg );
	
	public RoleGroup findById( Long id );
	
	public List<RoleGroup> findByParams( Map<String,Object> map );
	
	public List<RoleGroup> findByPage( Map<String,Object> map, int pageSize, int pageNo );
	
	public int countByParams( Map<String,Object> map );
	
	public int update( RoleGroup rg );
	
	public void addRole( List<Role> roles);
	
	public void addResource( List<ResourceRoleGroup> rrg );
	
	public int delete( Long id );
	
	public int deleteRole( Long id );
	
	public int deleteResource( Long id );
}
