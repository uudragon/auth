package com.uud.commons.office;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.StringUtils;


public class ExcelUtils {
	
	public static void writeMap( OutputStream out, String sheetName, 
								List<Map<String,Object>> data, String[] titles ) throws IOException{
		SXSSFWorkbook wb = new SXSSFWorkbook(200);
		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet( sheetName );
		Row row = sheet.createRow(0);
		for( int i = 0 ; i < titles.length ; i++ ){
			Cell cell = row.createCell( i );
			cell.setCellValue( titles[i] );
		}
		if( data != null ){
			for( int i = 0 ; i < data.size() ; i++ ){
				Map<String,Object> map = data.get( i );
				row = sheet.createRow( i + 1 );
				for( int j = 0 ; j < titles.length ; j++ ){
					if( map.containsKey( titles[j] ) ){
						Object value = map.get( titles[j] );
						Cell cell = row.createCell( j );
						cell.setCellValue( value.toString() );
					}
				}
			}
		}
		wb.write( out );
	}
	
	public static void writeObject( OutputStream out, String sheetName,
					List<Object> data, String[] titles ) throws IOException{
		SXSSFWorkbook wb = new SXSSFWorkbook(200);
		SXSSFSheet sheet = (SXSSFSheet) wb.createSheet( sheetName );
		Row row = sheet.createRow(0);
		for( int i = 0 ; i < titles.length ; i++ ){
			Cell cell = row.createCell( i );
			cell.setCellValue( titles[i] );
		}
		if( data != null ){
			for( int i = 0 ; i < data.size() ; i++ ){
				Object obj = data.get( i );
				row = sheet.createRow( i + 1 );
				for( int j = 0 ; j < titles.length ; j++ ){
					try{
						Object value = getProperty( obj,titles[j] );
						Cell cell = row.createCell( j );
						cell.setCellValue( value.toString() );
					} catch ( Exception e ){
						e.printStackTrace();
					}
				}
			}
		}
		wb.write( out );
	}
	
	private static Object getProperty( Object data, String property ) 
			throws NoSuchMethodException, SecurityException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clazz = data.getClass();
		if( data instanceof Map ){
			if( ((Map<?,?>)data).containsKey( property ) ){
				return ((Map<?,?>)data).get( property );
			} throw new NoSuchMethodException();
		} else {
			String methodName = "get" + StringUtils.capitalize( property );
			Method method = clazz.getMethod( methodName );
			return method.invoke( data );
		}
	}
	
}
