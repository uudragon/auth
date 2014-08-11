package com.uud.commons.office;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtilsTest {
	public static void main( String[] args ) throws IOException{
		File f = new File("D:/test.xlsx");
		FileOutputStream out = new FileOutputStream( f );
		String[] title = {"1","2"};
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("1", "test1");
		map.put("2", "test2");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add( map );
		ExcelUtils.writeMap( out, "test", list, title );
		out.close();
	}
}
