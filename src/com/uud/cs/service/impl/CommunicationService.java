package com.uud.cs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.cs.dao.ICommunicationDao;
import com.uud.cs.service.ICommunicationService;

@Service("communicationService")
public class CommunicationService implements ICommunicationService {
	
	@Autowired
	@Qualifier("communicationDao")
	private ICommunicationDao communicationDao;
	
	@Override
	public Long save(Map<String, Object> map) {
		return communicationDao.save( map );
	}

	@Override
	public List<Map<String, Object>> findCommnication(String code) {
		return communicationDao.findCommunication( code );
	}

}
