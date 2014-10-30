package com.uud.cs.service.impl.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.uud.cs.entity.TaskDetails;
import com.uud.cs.service.ITaskDetailsService;

public class TaskDetailsService {

	public static ITaskDetailsService service;
	static{
		ApplicationContext ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		service = (ITaskDetailsService) ctx.getBean( "taskDetailsService" );
	}
	public static void main( String args[] ){
		String name="test";
		Byte taskStatus = TaskDetails.TASK_STATUS_WAITING_DEAL;
		Byte taskType = TaskDetails.TASK_TYPE_CHECK;
		Date taskDate = new Date();
		String userNo = "test";
		String phone = "1212123";
		String content = "test";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("custmerName", name);
		map.put( "taskStatus", taskStatus);
		map.put("taskDate", taskDate);
		map.put("taskType", taskType);
		map.put("userNo", userNo);
		map.put("phone", phone);
		map.put("content", content);
		map.put("taskNo", UUID.randomUUID().toString() );
		service.save( map,(byte)1 );
	}
}
