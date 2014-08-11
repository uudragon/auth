package com.uud.auth.dao;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;
import com.uud.auth.entity.Role;

public interface IRoleDao {
	
	public Long save( Role role );
	
	public int update( Role role );
	
	public List<Role> select( Map<String,Object> map );
	
	public int count( Map<String,Object> map );
	
	public Role findById( Long id );
	
	public void addResource( List<ResourceRole> resource );
	
	public void removeResource( Long roleId );

	public List<ResourceRole> findRolePermissions();
	
	public List<Role> findByParams(Map<String, Object> map, int pageSize, int pageNo);
	
	public int delete( Long id );

	public int deleteResourceLink(Long id);

	public int deleteGroupLink(Long id);

	public int deleteUserLink(Long id);

	public int deleteUserGroupLink(Long id);
	
	public List<Role> findAll();
	
	public List<Resource> findResources( Long roleId );
}
