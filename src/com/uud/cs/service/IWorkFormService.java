package com.uud.cs.service;

import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.WorkForm;

public interface IWorkFormService {
	
	public Long save( Map<String,Object> map );
	
	public Page<WorkForm> findByPage( Map<String,Object> map, Integer pageSize, Integer pageNo );

	public Integer updateStatus(Integer status, Long id);
}
