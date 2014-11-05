package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

public interface ICommunicationDao {
	
	public Long save( Map<String,Object> map );
	
	public List<Map<String, Object>> findCommunication( String code );
}
