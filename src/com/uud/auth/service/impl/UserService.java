package com.uud.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.dao.IUserDao;
import com.uud.auth.dao.IUserGroupDao;
import com.uud.auth.entity.Page;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;
import com.uud.auth.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDao userDao;
	
	@Autowired
	@Qualifier("userGroupDao")
	private IUserGroupDao userGroupDao;
	
	public IUserGroupDao getUserGroupDao() {
		return userGroupDao;
	}
	
	public void setUserGroupDao(IUserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.uud.auth.service.IUserService#findById(long)
	 */
	@Override
	public User findById(long id) {
		
		return userDao.findById(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.uud.auth.service.IUserService#save(com.uud.auth.entity.User)
	 */
	@Override
	public Long save(User user) {
		Long id = userDao.save( user );
		user.setId( id );
		List<User> users = new ArrayList<User>();
		users.add(user);
		userGroupDao.addUser(users);
		return id;
	}
	
	@Override
	public List<User> findByGroupId(Long groupId) {
		
		return userDao.findByGroupId( groupId );
	}
	@Override
	public List<User> findByParams(Map<String, Object> map) {
		return userDao.findByParams( map );
	}
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	@Override
	public List<User> findUserRole() {
		return userDao.findUerRole();
	}
	
	@Override
	public Page<User> findByPage(Map<String, Object> map, Integer pageSize,
			Integer pageNo) {
		Page<User> page = new Page<User>();
		page.setPageSize( pageSize );
		page.setPageNo( pageNo );
		Integer recordscount = userDao.countByParams( map );
		page.setRecordsCount( recordscount );
		page.setPageNumber( recordscount / pageSize );
		List<User> rows = userDao.findByParams(map, pageSize, pageNo);
		for( User user : rows ){
			user.setPassword("");
		}
		page.setRecords( rows );
		return page;
	}
	
	@Override
	public int update(User user) {
		userGroupDao.removeUser( user.getId() );
		List<User> list = new ArrayList<User>();
		list.add( user );
		userGroupDao.addUser( list );
		return userDao.update( user );
	}
	@Override
	public int delete(Long userId) {
		return userDao.delete( userId );
	}

	@Override
	public List<Role> findRoles( Long userId ) {
		return userDao.findRole( userId );
	}
	
	@Override
	public void updateUserRoles( Long[] roleIds , Long userId ){
		userDao.removeRoles( userId );
		if( roleIds.length > 0 ){
			List<User> list = new ArrayList<User>();
			for( Long role : roleIds ){
				if( role != null ){
					User user = new User();
					user.setId( userId );
					user.setRoleId( role );
					list.add( user );
				}
			}
			if( list.size() > 0 ){
				userDao.addRoles( list );
			}
		}
	}
}
