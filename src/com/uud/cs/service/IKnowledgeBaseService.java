package com.uud.cs.service;

import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.KnowledgeBase;

public interface IKnowledgeBaseService {
	public Long save( Map<String,Object> map );
	public Page<KnowledgeBase> findByPage(Map<String, Object> map, Integer pageNo,
			Integer pageSize);
	public Integer updateKnowledgeBase(Map<String, Object> map);
	public Integer updateBN( Long id );
	public KnowledgeBase findById( Long id );
	public Integer deleteById(Long id);
}
