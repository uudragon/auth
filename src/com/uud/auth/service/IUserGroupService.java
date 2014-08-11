package com.uud.auth.service;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;
import com.uud.auth.entity.UserGroup;

public interface IUserGroupService {
	
	public Long save( UserGroup ug );
	
	public void addUser( List<User> user );
	
	public Page<UserGroup> search( Map<String,Object> map, int pageSize, int pageNo );
	
	public int update( UserGroup ug );
	
	public int delete( Long id );
	
	public List<UserGroup> findAll();

	public UserGroup findById(Long id);
	
	public List<Role> findRoles( Long id );
	
	public void updateUserRoles( Long[] roleIds, Long userId );
}
