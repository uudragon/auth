package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.KnowledgeBase;

public interface IKnowledgeBaseDao {
	public Long save( Map<String,Object> map );
	public List<KnowledgeBase> findByParams( Map<String,Object> map,Integer pageNo,Integer pageSize );
	public Integer countByParams(Map<String, Object> map);
	public Integer update(Map<String, Object> map);
	public Integer updateBN( Long id );
	public KnowledgeBase findById( Long id );
	public Integer delete( Long id );
}
