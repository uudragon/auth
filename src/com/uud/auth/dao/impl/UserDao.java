package com.uud.auth.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.auth.dao.IUserDao;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;

@Repository("userDao")
public class UserDao extends SqlMapClientDaoSupport implements IUserDao{
	
	@Autowired
	public UserDao(@Qualifier("sqlMapClient") SqlMapClient sqlMapClient) {
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.uud.auth.dao.IUserDao#findById(long)
	 */
	@Override
	public User findById( Long id ) {
		return (User) this.getSqlMapClientTemplate().queryForObject("user.findById",id);
	}

	@Override
	public Long save(User user) {
		return (Long) this.getSqlMapClientTemplate().insert("user.save",user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByGroupId(Long groupId) {
		
		return this.getSqlMapClientTemplate().queryForList("user.findByGroupId", groupId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByParams(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList( "user.findByParams", map );
	}

	@Override
	public Integer countByGroupId(Long groupId) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "user.countByGroupId", groupId );
	}

	@Override
	public Integer countByParams(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "user.countByParams", map );
	}

	@Override
	public Integer delete(Long id) {
		User user = new User();
		user.setId( id );
		user.setIsRemoved( true );
		return update( user );
	}
	
	@Override
	public Integer update( User user ){
		
		return this.getSqlMapClientTemplate().update( "user.update", user );
		
	}
	
	
	@Override
	public void addRoles(List<User> roles) {
		this.getSqlMapClientTemplate().insert( "user.addRoles", roles );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return this.getSqlMapClientTemplate().queryForList( "user.findAll" );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUerRole() {
		return this.getSqlMapClientTemplate().queryForList( "user.findAllUserRole" );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByParams(Map<String, Object> map, Integer pageSize,
			Integer pageNo) {
		return this.getSqlMapClientTemplate().queryForList( "user.findByParams", map,
				( pageNo -1 ) * pageSize , pageSize );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRole(Long userId) {
		return this.getSqlMapClientTemplate().queryForList( "user.findRole", userId );
	}

	@Override
	public int removeRoles( Long userId ) {
		return this.getSqlMapClientTemplate().delete( "user.removeRoles", userId );
	}

}
