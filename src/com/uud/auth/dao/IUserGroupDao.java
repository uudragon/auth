package com.uud.auth.dao;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;
import com.uud.auth.entity.UserGroup;

public interface IUserGroupDao {
	
	public List<UserGroup> findAll();
	
	public Long save( UserGroup ug );
	
	public void addUser( List<User> users );
	
	public int update( UserGroup ug );
	
	public List<UserGroup> findParams( Map<String,Object> map );
	
	public int countParams( Map<String,Object> map );
	
	public List<UserGroup> findParams( Map<String,Object> map,int pageSize,int pageNo );
	
	public UserGroup findById( Long id );
	
	public List<Role> findRoles( Long id );
	
	public void removeUser( Long userId, Long groupId );
	
	public void addRoles( List<UserGroup> roles );
	
	public int delete( Long id );

	public int deleteUser(Long groupId);

	public int deleteRole(Long groupId);

	public void removeUser(Long userId);
}
