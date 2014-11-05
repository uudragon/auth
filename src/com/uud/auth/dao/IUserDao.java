package com.uud.auth.dao;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;

public interface IUserDao {
	
	/**
	 * 按照主键查询
	 * @param id
	 * @return
	 */
	public User findById( Long id );
	
	public List<User> findByGroupId( Long groupId );
	
	public Integer countByGroupId( Long groupId );
	
	public List<User> findByParams( Map<String,Object> map );
	
	public List<User> findByParams( Map<String,Object> map,Integer pageSize,Integer pageNo );
	
	public Integer countByParams( Map<String,Object> map );
	
	public Integer update( User user );
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public Integer delete( Long id );
	/**
	 * 保存用户	
	 * @param user
	 * @return
	 */
	public Long save( User user );
	
	public void addRoles( List<User> roles );
	
	public int removeRoles( Long userId );
	
	public List<User> findAll();
	
	public List<User> findUerRole(); 
	
	public List<Role> findRole( Long userId );

}
