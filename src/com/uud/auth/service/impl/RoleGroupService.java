package com.uud.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.dao.IRoleGroupDao;
import com.uud.auth.entity.Page;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRoleGroup;
import com.uud.auth.entity.RoleGroup;
import com.uud.auth.service.IRoleGroupService;

@Service("rgService")
public class RoleGroupService implements IRoleGroupService {

	@Autowired
	@Qualifier("roleGroupDao")
	private IRoleGroupDao rgDao;
	
	@Override
	public Long save(RoleGroup rg) {
		
		return rgDao.save( rg );
	}

	@Override
	public Page<RoleGroup> findByPage(Map<String, Object> map, int pageSize,
			int pageNo) {
		
		Page<RoleGroup> page = new Page<RoleGroup>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		List<RoleGroup> list = rgDao.findByPage(map, pageSize, pageNo);
		int recordsCount = rgDao.countByParams( map ); 
		page.setRecordsCount( recordsCount );
		page.setRecords( list );
		return page;
	}

	@Override
	public int update(RoleGroup rg) {
		return rgDao.update( rg );
	}

	@Override
	public int delete(Long id) {
		int result = rgDao.delete( id );
		if( result > 0 ){
			rgDao.deleteResource( id );
			rgDao.deleteRole( id );
		}
		return result;
	}

	@Override
	public List<RoleGroup> findAll() {
		return rgDao.findAll();
	}

	@Override
	public List<Resource> findResources(Long roleId) {
		return rgDao.findResources( roleId );
	}

	@Override
	public void updateResource(Long[] resourceIds, Long groupId) {
		rgDao.deleteResource( groupId );
		
		if( resourceIds.length > 0 ){
			List<ResourceRoleGroup> list = new ArrayList<ResourceRoleGroup>();
			for( Long resourceId : resourceIds ){
				ResourceRoleGroup rrg = new ResourceRoleGroup();
				Resource resource = new Resource();
				resource.setId( resourceId );
				rrg.setResource( resource );
				RoleGroup rg = new RoleGroup();
				rg.setId( groupId );
				rrg.setRg(rg);
				list.add( rrg );
			}
			if( list.size() > 0 ){
				rgDao.addResource( list );
			}
		}
	}
	

}
