package com.uud.auth.impls.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uud.auth.entity.User;
import com.uud.auth.interfaces.load.IUserLoader;
import com.uud.auth.service.IUserService;
import com.uud.auth.servlet.ServiceBeanContext;

public class UserLoader implements IUserLoader {

	@Override
	public Map<Long,User> getAllAccount() {
		IUserService service = ServiceBeanContext.getInstance().getBean("userService");
		List<User> users = service.findAll();
		Map<Long,User> userMap = new HashMap<Long,User>();
		for( User user : users ){
			userMap.put( user.getId(), user );
		}
		return userMap;
	}

	@Override
	public ArrayList<String[]> getUserGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUserRoles(){
		IUserService service = ServiceBeanContext.getInstance().getBean("userService");
		return service.findUserRole();
	}


}
