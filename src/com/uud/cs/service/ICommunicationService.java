package com.uud.cs.service;

import java.util.List;
import java.util.Map;

public interface ICommunicationService {
	
	public Long save( Map<String,Object> map );
	
	public List<Map<String,Object>> findCommnication( String code );
}
