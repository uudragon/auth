package com.uud.auth.impls.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.User;

public class AccessLocalCache extends AbstractAccessCache{
	
	private static Logger LOG = Logger.getLogger( AccessLocalCache.class );
	
	private static Map<String,AuthMessage> map = new HashMap<String,AuthMessage>();
	
	@Override
	public String getPassword(String domain, String account) {
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
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
	public User getUser(String domain, String account) {
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
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

	@Override
	public User getUser(String domain, Long userId) {
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
			Map<Long,User> userMap = am.getUser_info();
			if( userMap != null ){
				return userMap.get( userId );
			}
		}
		return null;
	}

	@Override
	public Long[] getRoles(Long userId, String domain) {
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
			Map<Long, Long[]> map = am.getUser_role();
			return map.get( userId );
		}
		return null;
	}

	@Override
	public List<Long> getRole(String url, String method, String domain) {
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
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
	public List<Long> getRole(String code, String domain) {
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
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
	public List<Resource> getResources(String url, String method,
			String domain, Long userId) {
		List<Resource> resources = new ArrayList<Resource>();
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
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
	public List<Resource> getResources(String code, String domain, Long userId) {
		List<Resource> resources = new ArrayList<Resource>();
		if( containsKey( domain ) ){
			AuthMessage am = get( domain );
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
					if( r.getCode().equals( "code" ) ){
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
	public void clear() {
		map.clear();
	}

	@Override
	public void putAuthMessage(String domain, AuthMessage auth) {
		map.put( domain, auth );
		LOG.info( "load auth : " + JSON.toJSONString( map ) );
	}

	public boolean containsKey(String domain) {
		return map.containsKey( domain );
	}

	public AuthMessage get(String domain) {
		return map.get( domain );
	}
	
}
