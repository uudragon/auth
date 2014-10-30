package com.uud.cs.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.cs.dao.ITaskDetailsDao;
import com.uud.cs.service.ITaskDetailsService;

@Service("taskDetailsService")
public class TaskDetailsService implements ITaskDetailsService {

	@Autowired
	@Qualifier("taskDetailsDao")
	private ITaskDetailsDao taskDetailsDao;
	
	@Override
	public Long save( Map<String,Object> map, byte channel ) {
		map.put("taskNo", UUID.randomUUID().toString() );
		map.put("channel", channel );
		return taskDetailsDao.save( map );
	}

}
