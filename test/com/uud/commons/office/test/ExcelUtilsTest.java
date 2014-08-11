package com.uud.commons.office.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.uud.auth.entity.User;
import com.uud.commons.office.ExcelUtils;

public class ExcelUtilsTest {

	@Test
	public void testWriteMap() {
		OutputStream out = null;
		try {
			File file = new File("D:/test1.xls");
			out = new FileOutputStream( file );
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("test", "test");
		map1.put("test2", 1l);
		list.add( map1 );
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("test", "test");
		map2.put("test2", 1l);
		list.add( map2 );
		String[] titles = {"test","test2"};
		try {
			ExcelUtils.writeMap( out, "test", list, titles );
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testWriteObject() {
		OutputStream out = null;
		try {
			File file = new File("D:/test2.xls");
			out = new FileOutputStream( file );
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();
		User user = new User();
		user.setAccount("test");
		user.setEmail("123");
		user.setId( 1l );
		list.add( user );

		User user2 = new User();
		user2.setAccount("test");
		user2.setEmail("123");
		user2.setId( 1l );
		list.add( user2 );
		
		String[] titles = {"account","id","email"};
		try {
			ExcelUtils.writeObject(out, "test", list, titles);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
