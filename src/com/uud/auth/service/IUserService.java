package com.uud.auth.service;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;

public interface IUserService {
	
	/**
	 * 按照主键查询
	 * @param id
	 * @return
	 */
	public User findById( long id );
	
	public List<User> findByGroupId( Long groupId );
	
	public List<User> findByParams( Map<String,Object> map );
	/**
	 * 新建用户
	 * @param user
	 * @return
	 */
	public Long save( User user );
	
	public List<User> findAll();
	
	public List<User> findUserRole();
	
	public Page<User> findByPage( Map<String,Object> map, Integer pageSize, Integer pageNo );
	
	public int update( User user );
	
	public int delete( Long userId );

	public List<Role> findRoles(Long userId);

	public void updateUserRoles( Long[] roleIds , Long userId );
}
