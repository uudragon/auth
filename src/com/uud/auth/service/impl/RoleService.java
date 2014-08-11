package com.uud.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.dao.IRoleDao;
import com.uud.auth.dao.IRoleGroupDao;
import com.uud.auth.entity.Page;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;
import com.uud.auth.entity.Role;
import com.uud.auth.service.IRoleService;

@Service("roleService")
public class RoleService implements IRoleService {

	@Autowired
	@Qualifier("roleDao")
	private IRoleDao roleDao;
	
	@Autowired
	@Qualifier("roleGroupDao")
	private IRoleGroupDao rgDao;
	
	public IRoleGroupDao getRgDao() {
		return rgDao;
	}

	public void setRgDao(IRoleGroupDao rgDao) {
		this.rgDao = rgDao;
	}

	@Override
	public List<ResourceRole> findRolePermissions() {
		return roleDao.findRolePermissions();
	}

	@Override
	public Long save(Role role) {
		Long id = roleDao.save( role );
		List<Role> roles = new ArrayList<Role>();
		roles.add( role );
		role.setId( id );
		rgDao.addRole( roles );
		return id;
	}
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Page<Role> findByParams(Map<String, Object> map, int pageSize,
			int pageNo) {
		
		Page<Role> page = new Page<Role>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		List<Role> list = roleDao.findByParams( map, pageSize, pageNo );
		page.setRecords( list );
		
		int recordsCount = roleDao.count( map );
		page.setRecordsCount( recordsCount );
		
		return page;
	}

	@Override
	public int update(Role role) {
		roleDao.deleteGroupLink( role.getId() );
		List<Role> roles = new ArrayList<Role>();
		roles.add( role );
		rgDao.addRole( roles );
		return roleDao.update( role );
	}

	@Override
	public int delete(Long id) {
		int result = roleDao.delete( id );
		if( result > 0 ){
			roleDao.deleteGroupLink(id);
			roleDao.deleteResourceLink(id);
			roleDao.deleteUserGroupLink(id);
			roleDao.deleteUserLink(id);
		}
		return result;
	}
	
	@Override
	public List<Role> findAll(){
		return roleDao.findAll();
	}

	@Override
	public List<Resource> findResources(Long roleId) {
		return roleDao.findResources( roleId );
	}

	@Override
	public void updateResource( Long[] resourceIds , Long roleId ) {
		roleDao.removeResource( roleId );
		Role role = new Role();
		role.setId( roleId );
		if( resourceIds.length > 0 ){
			List<ResourceRole> resources = new ArrayList<ResourceRole>();
			for( Long resourceId : resourceIds ){
				if( resourceId != null ){
					ResourceRole rr = new ResourceRole();
					Resource resource = new Resource();
					resource.setId( resourceId );
					rr.setResource(resource);
					rr.setRole(role);
					resources.add( rr );
				}
			}
			if( resources.size() > 0 ){
				roleDao.addResource( resources );
			}
		}		
	}
}
