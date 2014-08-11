package com.uud.auth.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.auth.dao.IUserGroupDao;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;
import com.uud.auth.entity.UserGroup;

@Repository("userGroupDao")
public class UserGroupDao extends SqlMapClientDaoSupport implements IUserGroupDao {

	@Autowired
	public UserGroupDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(UserGroup ug) {
		return (Long) this.getSqlMapClientTemplate().insert("usergroup.save",ug);
	}

	@Override
	public void addUser(List<User> users) {
		
		this.getSqlMapClientTemplate().insert( "usergroup.addUser", users );
		
	}

	@Override
	public int update(UserGroup ug) {
		return this.getSqlMapClientTemplate().update( "usergroup.update", ug );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> findParams(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList( "usergroup.findByParams", map );
	}

	@Override
	public UserGroup findById(Long id) {
		return (UserGroup) this.getSqlMapClientTemplate().queryForObject( "usergroup.findById", id );
	}

	@Override
	public void removeUser(Long userId, Long groupId) {
		Map<String,Long> map = new HashMap<String,Long>();
		map.put( "USER_ID", userId );
		map.put( "GROUP_ID", groupId );
		this.getSqlMapClientTemplate().delete( "usergroup.delete", map );
	}
	
	@Override
	public void removeUser( Long userId ) {
		this.getSqlMapClientTemplate().delete( "user.deleteUserGroup", userId );
	}
	
	@Override
	public void addRoles(List<UserGroup> roles) {
		this.getSqlMapClientTemplate().insert( "usergroup.addRoles", roles );
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> findParams(Map<String, Object> map, int pageSize,
			int pageNo) {
		
		return this.getSqlMapClientTemplate().queryForList( "usergroup.findByParams", map, 
				( pageNo - 1 ) * pageSize, pageSize );
	}

	@Override
	public int countParams(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "usergroup.countByParams", map );
	}

	@Override
	public int delete(Long id) {
		return this.getSqlMapClientTemplate().delete( "usergroup.deleteById", id );
	}
	
	@Override
	public int deleteUser( Long groupId ){
		return this.getSqlMapClientTemplate().delete( "usergroup.deleteUser", groupId );
	}
	
	@Override
	public int deleteRole( Long groupId ){
		return this.getSqlMapClientTemplate().delete( "usergroup.deleteRole", groupId );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> findAll() {
		return this.getSqlMapClientTemplate().queryForList( "usergroup.findAll");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoles(Long id) {
		return this.getSqlMapClientTemplate().queryForList( "usergroup.findRoles", id );
	}

}
