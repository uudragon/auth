package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.Message;
import com.uud.cs.entity.MessageTemplate;

public interface IMessageDao {
	
	public Long save( Map<String,Object> map );
	
	public Long saveTemplate( Map<String,Object> map );
	
	public Integer count( Map<String,Object> map );
	
	public List<Message> findByParams( Map<String,Object> map, int pageSize, int pageNo );
	
	public List<MessageTemplate> findTemplate();
}
