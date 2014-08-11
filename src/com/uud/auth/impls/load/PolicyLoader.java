package com.uud.auth.impls.load;

import java.util.ArrayList;
import java.util.List;

import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;
import com.uud.auth.interfaces.load.IPolicyLoader;
import com.uud.auth.service.IResourceService;
import com.uud.auth.service.IRoleService;
import com.uud.auth.servlet.ServiceBeanContext;

public class PolicyLoader implements IPolicyLoader {

	@Override
	public ArrayList<String> getRoles(){
		// TODO Auto-generated method stub 
		return null;
	}

	@Override
	public  List<ResourceRole> getPermissions(){
		IRoleService roleService = ServiceBeanContext.getInstance().getBean("roleService");
		return roleService.findRolePermissions();
	}

	@Override
	public List<Resource> getAllResourceList(){
		IResourceService resourceService = ServiceBeanContext.getInstance().getBean("resourceService");
		return resourceService.findAll();
	}

}
