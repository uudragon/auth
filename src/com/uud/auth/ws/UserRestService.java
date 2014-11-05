package com.uud.auth.ws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uud.auth.entity.User;
import com.uud.auth.service.IUserService;
import com.uud.auth.servlet.ServiceBeanContext;

@Path("user")
public class UserRestService {
	
	private IUserService userService = ServiceBeanContext.getInstance().getBean("userService");
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> findAllUser(){
		return userService.findAll();
	}
}
