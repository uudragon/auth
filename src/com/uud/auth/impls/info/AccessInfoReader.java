package com.uud.auth.impls.info;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;
import com.uud.auth.entity.User;
import com.uud.auth.impls.load.PolicyLoader;
import com.uud.auth.impls.load.UserLoader;
import com.uud.auth.interfaces.info.IAccessInfoReader;
import com.uud.auth.interfaces.load.IPolicyLoader;
import com.uud.auth.interfaces.load.IUserLoader;


public class AccessInfoReader implements IAccessInfoReader {
	
	private static AccessInfoReader _instance;
	
	private IUserLoader userLoader;
	
	private IPolicyLoader policyLoader;
	
	private AccessInfoReader(){
		userLoader = new UserLoader();
		policyLoader = new PolicyLoader();
	}
	
	public static AccessInfoReader getInstance(){
		if( _instance == null ){
			synchronized( AccessInfoReader.class ){
				if( _instance == null ){
					_instance = new AccessInfoReader();
				}
			}
		}
		return _instance;
	}
	
	@Override
	public String getAccessInfo(){
		List<ResourceRole> role_resource = policyLoader.getPermissions();
		Map<Long,List<Long>> map = new HashMap<Long,List<Long>>();
		for( ResourceRole rr : role_resource){
			Long roleId = rr.getRole().getId();
			if( map.containsKey( roleId ) ){
				map.get( roleId ).add( rr.getResource().getId() );
			} else {
				List<Long> list = new ArrayList<Long>();
				list.add( rr.getResource().getId() );
				map.put( rr.getRole().getId() , list );
			}
		}
		return JSON.toJSONString( map );
	}
	
	
	
	@Override
	public String getAccountInfo() {
		return JSON.toJSONString( userLoader.getAllAccount() );
	}

	@Override
	public String getRoleInfo() {
		List<User> role_info = userLoader.getUserRoles();
		Map<Long,List<Long>> ur = new HashMap<Long,List<Long>>();
		for( User user : role_info ){
			if( ur.containsKey( user.getId() ) ){
				ur.get( user.getId() ).add( user.getRoleId() );
			} else {
				List<Long> roles = new ArrayList<Long>();
				roles.add( user.getRoleId() );
				ur.put( user.getId() , roles );
			}
		}
		return JSON.toJSONString( ur );
	}

	@Override
	public String getResourceInfo() {
		List<Resource> list = policyLoader.getAllResourceList();
		Map<Long,Resource> map = new HashMap<Long,Resource>();
		for( Resource res : list ){
			map.put( res.getId(), res );
		}
		return JSON.toJSONString( map );
	}

}
