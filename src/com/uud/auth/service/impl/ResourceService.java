package com.uud.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.dao.IResourceDao;
import com.uud.auth.entity.Resource;
import com.uud.auth.service.IResourceService;

@Service("resourceService")
public class ResourceService implements IResourceService {

	@Autowired
	@Qualifier("resourceDao")
	private IResourceDao resourceDao;

	
	@Override
	public List<Resource> findAll() {
		
		return resourceDao.findAll();
	}

	@Override
	public Long save(Resource r) {
		return resourceDao.save( r );
	}

	@Override
	public int update(Resource r) {
		return resourceDao.update( r );
	}

	@Override
	public int delete(Long id) {
		resourceDao.deleteRoleResource( id );
		resourceDao.deleteRoleGroupResource( id );
		deleteChild( id );
		int r = resourceDao.deleteById( id );
		return r;
	}
	
	private void deleteChild( Long parentId ){
		List<Resource> list = resourceDao.findByParent( parentId );
		for( Resource r : list ){
			resourceDao.deleteRoleResource( r.getId() );
			resourceDao.deleteRoleGroupResource( r.getId() );
			resourceDao.deleteById( r.getId() );
			this.deleteChild( r.getId() );
		}
	}
	
	@Override
	public Resource findById(Long id) {
		return resourceDao.findById( id );
	}



}
