package com.uud.auth.interfaces.load;

import java.util.ArrayList;
import java.util.List;

import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;


public interface IPolicyLoader {

	public ArrayList<String> getRoles();


	public List<ResourceRole> getPermissions();


	public List<Resource> getAllResourceList();

}
