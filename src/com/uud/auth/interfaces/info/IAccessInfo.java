package com.uud.auth.interfaces.info;

import java.util.List;

import com.uud.auth.entity.Resource;
import com.uud.auth.entity.User;


public interface IAccessInfo {

	/**
	 * 如果业务id对应的是userid，获取该userid的password。<br>
	 * 如果光业务id不是userid，就返回“”；
	 * 
	 * 
	 * @return
	 */
	public String getPassword( String domain, String account );
	
	public User getUser( String domain, String account);
	/**
	 * 新加接口方法用于加载权限配置信息。
	 * 
	 */
	public void load();

	public User getUser(String domain, Long userId);

	public Long[] getRoles(Long userId, String domain);
	
	public List<Long> getRole( String url, String method, String domain );

	public List<Long> getRole( String code, String domain );
	
	public List<Resource> getResources(String url, String method, String domain,
			Long userId);
	public List<Resource> getResources(String code, String domain,
			Long userId);

	public List<Resource> getResources(String domain, Long userId);
}
