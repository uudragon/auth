package com.uud.cs.service;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.Message;
import com.uud.cs.entity.MessageTemplate;

public interface IMessageService {
	
	public void save( Map<String,Object> map );
	public Long saveTemplate( Map<String,Object> map );
	
	public Page<Message> findByParams( Map<String,Object> map, int pageSize, int pageNo );
	
	public List<MessageTemplate> findTemplate();
}
