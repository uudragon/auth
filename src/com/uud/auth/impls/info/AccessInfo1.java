package com.uud.auth.impls.info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.uud.auth.interfaces.load.LoadFailedException;

public class AccessInfo1 implements IAccessInfo {
	
	private static IAccessInfo instance = null;
	
	private static Logger logger = Logger.getLogger(AccessInfo1.class);
	
	private Map<String,AuthMessage> map = new HashMap<String,AuthMessage>();
	
	
	private AccessInfo1(){
		
	}
	/**
	 * 默认情况需要在初始化时加载权限信息。
	 * @return
	 * @throws LoadFailedException
	 */
	public static IAccessInfo getInstance() {
		return getInstance(true);
	}

	/**
	 * 实例化时，需不需要加载权限信息。 1、除了ReloadInvoker之外，都需要在初始化时加载权限信息。
	 * 
	 * @param shouldBeload
	 * @return
	 * @throws LoadFailedException
	 */
	public static IAccessInfo getInstance(boolean shouldBeload) {
		if (instance == null)
			synchronized (AccessInfo1.class) {
				if (instance == null) {
					instance = new AccessInfo1();
					if (shouldBeload)
						instance.load();
				}
			}
		return instance;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.accessmanager.interfaces.client.IAccessInfo#load(java.io.InputStream)
	 */
	public void load() {
		map.clear();
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
					map.put( domain, auth );
				} catch ( Exception e ){
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		logger.info( " auth load ! \n" + JSON.toJSONString( map ) + "\n" );
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
	/**
	 * 深拷贝
	 * 
	 * @param info
	 * @return
	 */
	/*private Map deepCopy(Map info) {
		Map temp = new Hashtable();
		Collection collection = info.keySet();
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object value = info.get(key);
			temp.put(key, value);
		}
		return temp;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dc.accessmanager.interfaces.client.IAccessInfo#getPassword(java.lang.String)
	 */
	public String getPassword(String domain,String account) {
		
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long, User> userMap = am.getUser_info();
			if( userMap != null ){
				Set<Entry<Long,User>> set = userMap.entrySet();
				Iterator<Entry<Long,User>> iterator = set.iterator();
				while( iterator.hasNext() ){
					Entry<Long,User> entry = iterator.next();
					User user = entry.getValue();
					if( user.getAccount().equals( account ) ){
						return user.getPassword();
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public User getUser( String domain , Long userId ){
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long,User> userMap = am.getUser_info();
			if( userMap != null ){
				return userMap.get( userId );
			}
		}
		return null;
	}
	
	@Override
	public User getUser(String domain, String account) {
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long, User> userMap = am.getUser_info();
			if( userMap != null ){
				Set<Entry<Long,User>> set = userMap.entrySet();
				Iterator<Entry<Long,User>> iterator = set.iterator();
				while( iterator.hasNext() ){
					Entry<Long,User> entry = iterator.next();
					User user = entry.getValue();
					if( user.getAccount().equals( account ) ){
						return user;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 装载缓存信息
	 * @see com.uud.auth.interfaces.info.dc.accessmanager.interfaces.client.IAccessInfo#reload(java.io.InputStream, java.io.InputStream)
	 */
	public void reload(){
		map.clear();
		try {
			instance.load();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (logger.isInfoEnabled())
			logger.info("load access info finished...");
	}
	
	@Override
	public Long[] getRoles( Long userId ,String domain ) {
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long, Long[]> map = am.getUser_role();
			return map.get( userId );
		}
		return null;
	}
	
	@Override
	public List<Resource> getResources( String url, String method, String domain, Long userId ){
		List<Resource> resources = new ArrayList<Resource>();
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long, Resource> resMap = am.getResources();
			Set<Long> keySet = resMap.keySet();
			Iterator<Long> iterator = keySet.iterator();
			Long parentId = null;
			if( url == null ){
				parentId = 0l;
			} else {
				while( iterator.hasNext() ){
					Long key = iterator.next();
					Resource r = resMap.get( key );
					if( r.getUrl().equals( url ) 
							&& r.getMethod().equals( method ) ){
						parentId = r.getId();
					}
				}
			}
			if( parentId != null ){
				Map<Long, Long[]> map = am.getUser_role();
				Long[] roleIds = map.get( userId );
				map = am.getRole_resource();
				if( roleIds != null ){
					for( Long roleId : roleIds ){
						Long[] resIds = map.get(roleId);
						if( resIds != null ){
							for( Long resId : resIds ){
								Resource resource = resMap.get( resId );
								if( resource.getParent().equals( parentId ) ){
									resources.add( resource );
								}
							}
						}
					}
				}
			}
		}
		return resources;
	}
	
	@Override
	public List<Long> getRole(String url, String method, String domain) {
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long, Resource> res = am.getResources();
			Set<Entry<Long,Resource>> set = res.entrySet();
			Iterator<Entry<Long,Resource>> iterator = set.iterator();
			Long resourceId = null;
			while( iterator.hasNext() ){
				Entry<Long,Resource> entry = iterator.next();
				Resource resource = entry.getValue();
				if( url.equals( resource.getUrl() ) 
						&& method.equals( resource.getMethod() ) ){
					resourceId = resource.getId();
					break;
				}
			}
			List<Long> resourceList = new ArrayList<Long>();
			if( resourceId != null ){
				Map<Long,Long[]> rr = am.getRole_resource();
				Set<Entry<Long,Long[]>> rrSet = rr.entrySet();
				Iterator<Entry<Long,Long[]>> rrIterator = rrSet.iterator();
				while( rrIterator.hasNext() ){
					Entry<Long,Long[]> rrEntry = rrIterator.next();
					Long[] resList = rrEntry.getValue();
					for( Long resId : resList ){
						if( resourceId.equals( resId ) ){
							resourceList.add( rrEntry.getKey() );
						}
					}
				}
			}
			return resourceList;
		}
		return null;
	}
	@Override
	public List<Resource> getResources(String code, String domain, Long userId) {
		List<Resource> resources = new ArrayList<Resource>();
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long, Resource> resMap = am.getResources();
			Set<Long> keySet = resMap.keySet();
			Iterator<Long> iterator = keySet.iterator();
			Long parentId = null;
			if( code == null ){
				parentId = 0l;
			} else {
				while( iterator.hasNext() ){
					Long key = iterator.next();
					Resource r = resMap.get( key );
					if( r.getCode().equals( code ) ){
						parentId = r.getId();
					}
				}
			}
			if( parentId != null ){
				Map<Long, Long[]> map = am.getUser_role();
				Long[] roleIds = map.get( userId );
				map = am.getRole_resource();
				if( roleIds != null ){
					for( Long roleId : roleIds ){
						Long[] resIds = map.get(roleId);
						if( resIds != null ){
							for( Long resId : resIds ){
								Resource resource = resMap.get( resId );
								if( resource.getParent().equals( parentId ) ){
									resources.add( resource );
								}
							}
						}
					}
				}
			}
		}
		return resources;
	}
	@Override
	public List<Long> getRole(String code, String domain) {
		if( map.containsKey( domain ) ){
			AuthMessage am = map.get( domain );
			Map<Long, Resource> res = am.getResources();
			Set<Entry<Long,Resource>> set = res.entrySet();
			Iterator<Entry<Long,Resource>> iterator = set.iterator();
			Long resourceId = null;
			while( iterator.hasNext() ){
				Entry<Long,Resource> entry = iterator.next();
				Resource resource = entry.getValue();
				if( code.equals( resource.getCode() ) ){
					resourceId = resource.getId();
					break;
				}
			}
			List<Long> resourceList = new ArrayList<Long>();
			if( resourceId != null ){
				Map<Long,Long[]> rr = am.getRole_resource();
				Set<Entry<Long,Long[]>> rrSet = rr.entrySet();
				Iterator<Entry<Long,Long[]>> rrIterator = rrSet.iterator();
				while( rrIterator.hasNext() ){
					Entry<Long,Long[]> rrEntry = rrIterator.next();
					Long[] resList = rrEntry.getValue();
					for( Long resId : resList ){
						if( resourceId.equals( resId ) ){
							resourceList.add( rrEntry.getKey() );
						}
					}
				}
			}
			return resourceList;
		}
		return null;
	}
	@Override
	public List<Resource> getResources(String domain, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
class AuthMessage{
	
	private Map<Long,User> user_info;
	
	private Map<Long,Long[]> user_role;
	
	private Map<Long,Long[]> role_resource;
	
	private Map<Long,Resource> resources;

	public Map<Long, User> getUser_info() {
		return user_info;
	}

	public void setUser_info(Map<Long, User> user_info) {
		this.user_info = user_info;
	}

	public Map<Long, Resource> getResources() {
		return resources;
	}

	public void setResources(Map<Long, Resource> resources) {
		this.resources = resources;
	}

	public Map<Long,Long[]> getRole_resource() {
		return role_resource;
	}

	public void setRole_resource(Map<Long,Long[]> role_resource) {
		this.role_resource = role_resource;
	}

	public Map<Long,Long[]> getUser_role() {
		return user_role;
	}

	public void setUser_role(Map<Long,Long[]> user_role) {
		this.user_role = user_role;
	}
 	
}
