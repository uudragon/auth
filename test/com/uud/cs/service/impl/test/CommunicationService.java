package com.uud.cs.service.impl.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.uud.cs.entity.CommunicationDetail;
import com.uud.cs.service.ICommunicationService;

public class CommunicationService {
	
	public static ICommunicationService service;
	
	static{
		ApplicationContext ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		service = (ICommunicationService) ctx.getBean( "communicationService" );
	}
	
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String,Object>();
		String theme = "test";
		Date startTime = new Date();
		String phoneStatus = CommunicationDetail.PHONE_STATUS_ANSWER;
		Date nextTime = new Date();
		String content = "test";
		String dealResult = "test";
		String userNo = "test";
		String complainType = "test";
		Integer dealLevel= CommunicationDetail.DEAL_LEVEL_1;
		
		map.put("theme", theme);
		map.put("startTime", startTime);
		map.put("phoneStatus", phoneStatus);
		map.put("nextTime", nextTime);
		map.put("content", content);
		map.put("dealResult", dealResult);
		map.put("userNo", userNo);
		map.put("complainType", complainType);
		map.put("dealLevel", dealLevel);
		map.put("custmerCode", "test");
		map.put("chatType", CommunicationDetail.CHAT_TYPE_CHECK);
		service.save( map );
	}

}
