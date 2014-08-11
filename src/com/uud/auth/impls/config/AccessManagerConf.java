package com.uud.auth.impls.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.uud.auth.interfaces.config.IAccessManagerConf;

public class AccessManagerConf implements IAccessManagerConf {
	
	private static IAccessManagerConf conf = null;
	
	private Properties pro = null;
	
	private AccessManagerConf() {
	}
	
	public static IAccessManagerConf getInstance() throws IOException {
		if (conf == null)
			synchronized (AccessManagerConf.class) {
				if (conf == null)
					conf = new AccessManagerConf();
			}
		return conf;
	}
	
	@Override
	public String getInstallRoot() {
		
		return System.getProperty( ACCESS_SYSTEM_ATTRIBUTE );
		
	}
	
	private void load() throws IOException {
		if (pro == null) {
			pro = new Properties();
			InputStream in = null;
			try {
				File file = new File(
						getInstallRoot() + File.separator + "conf",
						CONFIGURATION_NAME);
				in = new FileInputStream(file);
				if (in != null)
					pro.load(in);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					in.close();
			}
		}
	}

	@Override
	public String getProperty(String prop) throws IOException {
		load();
		if (pro.containsKey(prop))
			return pro.getProperty(prop);
		else
			return null;
	}

	@Override
	public Properties getProperties() throws IOException {
		if (pro == null)
			load();
		return pro;
	}

}
