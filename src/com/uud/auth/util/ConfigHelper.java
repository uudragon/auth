package com.uud.auth.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class ConfigHelper {
	
	private Properties properties = new Properties();
	
	private static ConfigHelper _instance;
	
	private ConfigHelper(){
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws IOException, URISyntaxException{
		URL filePath = this.getClass().getClassLoader().getResource("config.properties");
		FileInputStream in = new FileInputStream( new File(filePath.toURI()) );
		properties.load( in );
	}
	
	public Long getLong( String key ){
		return Long.parseLong( properties.getProperty( key ) );
	}
	
	public boolean getBoolean( String key ){
		return Boolean.parseBoolean( properties.getProperty( key ) );
	}
	
	public Integer getInteger( String key ){
		return Integer.parseInt( properties.getProperty( key ) );
	}
	
	public String getString( String key ){
		return properties.getProperty( key );
	}
	
	public static ConfigHelper getInstance(){
		if( _instance == null ){
			synchronized( ConfigHelper.class ){
				if( _instance == null ){
					_instance = new ConfigHelper();
				}
			}
		}
		return _instance;
	}
}
