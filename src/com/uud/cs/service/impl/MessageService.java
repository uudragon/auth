package com.uud.cs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
import com.uud.cs.dao.IMessageDao;
import com.uud.cs.entity.Message;
import com.uud.cs.entity.MessageTemplate;
import com.uud.cs.service.IMessageService;

@Service("messageService")
public class MessageService implements IMessageService {

	@Autowired
	@Qualifier("messageDao")
	private IMessageDao messageDao;
	
	
	@Override
	public void save(Map<String, Object> map) {
		String targets = (String) map.get("target");
		String[] ts = targets.split(",");
		for( String t : ts ){
			map.put( "target", t );
			messageDao.save( map );
		}
	}

	@Override
	public Long saveTemplate(Map<String, Object> map) {
		return messageDao.saveTemplate( map );
	}

	@Override
	public Page<Message> findByParams(Map<String, Object> map, int pageSize,
			int pageNo) {
		Page<Message> page = new Page<Message>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		
		Integer count = messageDao.count( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize == 0 ? count / pageSize : count / pageSize + 1 );
		
		page.setRecords( messageDao.findByParams( map, pageSize, pageNo ) );
		return page;
	}

	@Override
	public List<MessageTemplate> findTemplate() {
		return messageDao.findTemplate();
	}

}
