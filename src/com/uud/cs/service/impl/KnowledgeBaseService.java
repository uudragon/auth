package com.uud.cs.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
import com.uud.cs.dao.IKnowledgeBaseDao;
import com.uud.cs.entity.KnowledgeBase;
import com.uud.cs.service.IKnowledgeBaseService;

@Service("knowledgeBaseService")
public class KnowledgeBaseService implements IKnowledgeBaseService {

	@Autowired
	@Qualifier("knowledgeBaseDao")
	private IKnowledgeBaseDao knowledgeBaseDao;

	public IKnowledgeBaseDao getKnowledgeBaseDao() {
		return knowledgeBaseDao;
	}
	
	public void setKnowledgeBaseDao(IKnowledgeBaseDao knowledgeBaseDao) {
		this.knowledgeBaseDao = knowledgeBaseDao;
	}

	@Override
	public Long save(Map<String, Object> map) {
		Long id = knowledgeBaseDao.save( map );
		return id;
	}

	@Override
	public Page<KnowledgeBase> findByPage(Map<String, Object> map,Integer pageNo,Integer pageSize) {
		Page<KnowledgeBase> page = new Page<KnowledgeBase>();
		if( pageNo == null ){
			pageNo = 1;
		}
		if( pageSize == null ){
			pageSize = 10; 
		}
		page.setPageSize( pageSize );
		page.setPageNo( pageNo );
		int count = knowledgeBaseDao.countByParams( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize == 0 ? count / pageSize : count / pageSize + 1 );
		page.setRecords( knowledgeBaseDao.findByParams( map, pageNo, pageSize) );
		return page;
	}
	
	@Override
	public Integer updateKnowledgeBase( Map<String,Object> map ){
		return knowledgeBaseDao.update(map);
	}
	
	@Override
	public Integer updateBN( Long id ){
		return knowledgeBaseDao.updateBN( id );
	}
	
	@Override
	public KnowledgeBase findById( Long id ){
		KnowledgeBase kb = knowledgeBaseDao.findById( id );
		knowledgeBaseDao.updateBN( id );
		return kb;
	}
	
	@Override
	public Integer deleteById( Long id ){
		return knowledgeBaseDao.delete( id );
	}

}
