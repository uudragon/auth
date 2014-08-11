package com.uud.auth.impls.info;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.User;
import com.uud.auth.interfaces.info.IAccessInfo;

public abstract class AbstractAccessCache implements IAccessInfo{
	
	private static Logger logger = Logger.getLogger(AbstractAccessCache.class);
	
	public abstract void clear();
	
	public abstract void putAuthMessage( String domain, AuthMessage auth );
	
	public void reload(){
		clear();
		try {
			load();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (logger.isInfoEnabled())
			logger.info("load access info finished...");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.accessmanager.interfaces.client.IAccessInfo#load(java.io.InputStream)
	 */
	public void load() {
		clear();
		try {
			Map<String,DomainConfig> config = DomainConfigInfo.getInstance().getConfig();
			Set<Entry<String, DomainConfig>> set = config.entrySet();
			Iterator<Entry<String, DomainConfig>> iterator = set.iterator();
			while( iterator.hasNext() ){
				Entry<String,DomainConfig> entry = iterator.next();
				DomainConfig domainConfig = entry.getValue();
				String domain = entry.getKey();
				try {
					String accounts = "";
					String access="";
					String role_user = "";
					String resource = "";
					if( domainConfig.isLoaction() ){
						accounts = AccessInfoReader.getInstance().getAccountInfo();
						access = AccessInfoReader.getInstance().getAccessInfo();
						role_user = AccessInfoReader.getInstance().getRoleInfo();
						resource = AccessInfoReader.getInstance().getResourceInfo();
					} else {
						//TODO 非本地权限远程加载
					}
					AuthMessage auth = load(accounts,role_user,access,resource);
					putAuthMessage( domain, auth );
				} catch ( Exception e ){
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		logger.info( " auth load end!" );
	}

	private AuthMessage load( String accounts, String role_user, String access, String resource )
			throws ParserConfigurationException, SAXException, IOException {
		AuthMessage auth = new AuthMessage();
		if (accounts != null && !"".equals(accounts))
			auth.setUser_info( loadAccount(accounts) );
		if (role_user != null && !"".equals(role_user))
			auth.setUser_role( loadRole(role_user) );
		if (access != null && !"".equals(access) )
			auth.setRole_resource( loadAccess(access) );
		if (resource != null && !"".equals( resource ) )
			auth.setResources( loadResource(resource) );
		return auth;
	}
	
	private Map<Long,Long[]> loadRole( String user_role ) {
		return JSON.parseObject( user_role, new TypeReference<Map<Long,Long[]>>(){});
	}
	private Map<Long,Long[]> loadAccess(String access) {
		return JSON.parseObject( access, new TypeReference<Map<Long,Long[]>>(){});
	}
	private Map<Long,User> loadAccount(String accounts){
		return JSON.parseObject( accounts, new TypeReference<Map<Long,User>>(){});
	}
	private Map<Long,Resource> loadResource(String resources){
		return JSON.parseObject( resources, new TypeReference<Map<Long,Resource>>(){});
	}
}
