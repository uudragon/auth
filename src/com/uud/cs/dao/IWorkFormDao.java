package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.WorkForm;

public interface IWorkFormDao {
	public Long save( Map<String,Object> map );

	public List<WorkForm> findByPage(Map<String, Object> map, Integer pageSize,
			Integer pageNo);

	public Integer countByPage(Map<String, Object> map);
}
