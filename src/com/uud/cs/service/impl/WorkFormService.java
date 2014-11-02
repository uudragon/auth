package com.uud.cs.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.cs.dao.ICommunicationDao;
import com.uud.cs.dao.IWorkFormDao;
import com.uud.cs.service.IWorkFormService;

@Service("workFormService")
public class WorkFormService implements IWorkFormService{
	
	@Autowired
	@Qualifier("workFormDao")
	private IWorkFormDao workFormDao;

	@Autowired
	@Qualifier("communicationDao")
	private ICommunicationDao communicationDao;
	
	@Override
	public Long save(Map<String, Object> map) {
		String code = UUID.randomUUID().toString();
		map.put( "code", code );
		Long id = workFormDao.save( map );
		Map<String,Object> communication = new HashMap<String,Object>();
		communication.put("tel_no", map.get("phone") );
		communication.put("tel_status", map.get("tel_status") );
		communication.put("start_time", map.get("start_time") );
		communication.put("form_no", code );
		communication.put("user_no", map.get("user") );
		communication.put("content", map.get("content") );
		communication.put("theme", map.get("theme") );
		communicationDao.save( communication );
		return id;
	}
	
}
