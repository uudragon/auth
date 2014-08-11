package com.uud.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.dao.IUserGroupDao;
import com.uud.auth.entity.Page;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;
import com.uud.auth.entity.UserGroup;
import com.uud.auth.service.IUserGroupService;

@Service("userGroupService")
public class UserGroupService implements IUserGroupService {
	
	@Autowired
	@Qualifier("userGroupDao")
	private IUserGroupDao ugdao;

	@Override
	public Long save(UserGroup ug) {
		return ugdao.save(ug);
	}
	
	@Override
	public void addUser(List<User> user) {
		ugdao.addUser(user);
	}
	
	public IUserGroupDao getUgdao() {
		return ugdao;
	}

	public void setUgdao(IUserGroupDao ugdao) {
		this.ugdao = ugdao;
	}

	@Override
	public Page<UserGroup> search(Map<String, Object> map, int pageSize,
			int pageNo) {
		Page<UserGroup> page = new Page<UserGroup>();
		page.setPageSize( pageSize );
		int recordsCount = ugdao.countParams( map );
		page.setRecordsCount( recordsCount );
		List<UserGroup> uglist = ugdao.findParams(map, pageSize, pageNo);
		page.setRecords( uglist );
		return page;
	}

	@Override
	public int update(UserGroup ug) {
		
		return ugdao.update( ug );
	}

	@Override
	public int delete(Long id) {
		int result = ugdao.delete( id );
		ugdao.deleteUser( id );
		ugdao.deleteRole( id );
		return result;
	}

	@Override
	public UserGroup findById(Long id) {
		
		return ugdao.findById( id );
	}

	@Override
	public List<UserGroup> findAll() {
		return ugdao.findAll();
	}

	@Override
	public List<Role> findRoles(Long id) {
		return ugdao.findRoles( id );
	}

	@Override
	public void updateUserRoles(Long[] roleIds, Long groupId ) {
		ugdao.deleteRole( groupId );
		if( roleIds.length > 0 ){
			List<UserGroup> roles = new ArrayList<UserGroup>();
			for( Long roleId : roleIds ){
				UserGroup ug = new UserGroup();
				ug.setRoleId( roleId );
				ug.setId( groupId );
				roles.add( ug );
			}
			ugdao.addRoles( roles );
		}
	}

}
