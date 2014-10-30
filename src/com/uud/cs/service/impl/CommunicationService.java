package com.uud.cs.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.cs.dao.ICommunicationDetailDao;
import com.uud.cs.service.ICommunicationService;

@Service("communicationService")
public class CommunicationService implements ICommunicationService {
	
	@Autowired
	@Qualifier("communicationDetailDao")
	private ICommunicationDetailDao communicationDetailDao;
	
	@Override
	public Long save(Map<String, Object> map) {
		return communicationDetailDao.save( map );
	}

}
