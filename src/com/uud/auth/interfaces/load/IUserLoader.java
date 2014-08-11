package com.uud.auth.interfaces.load;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.uud.auth.entity.User;


public interface IUserLoader {

	public Map<Long,User> getAllAccount();

	public ArrayList<String[]> getUserGroups();

	public List<User> getUserRoles();

}
