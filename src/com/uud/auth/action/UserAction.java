package com.uud.auth.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.uud.auth.entity.Page;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.User;
import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.service.IUserService;
import com.uud.auth.util.MD5;

@Controller("userAction")
public class UserAction {
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	public void findById( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter("id");
		User user = userService.findById( Long.parseLong( id ) );
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( user ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void saveUser( HttpServletRequest request,HttpServletResponse response ){
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		password = MD5.getInstance().getMD5Code( password );
		String sex = request.getParameter("gender");
		String positions = request.getParameter("positions");
		String active = request.getParameter("isValid");
		String email = request.getParameter("email");
		String groupId = request.getParameter("groupId");
		request.getParameterMap();
		User user = new User();
		user.setGroupId( Long.parseLong( groupId ) );
		user.setAccount( account );
		user.setEmail(email);
		user.setPassword(password);
		user.setGender( Integer.parseInt( sex ) );
		user.setName(name);
		user.setPositions( positions );
		user.setIsValid( Boolean.parseBoolean( active ) );
		Long id = userService.save( user );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( id != null ){
			rm.setResult( true );
		} else {
			rm.setResult( false );
			rm.setMessage( "save user failed!" );
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( rm ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void searchUser( HttpServletRequest request,HttpServletResponse response ){
		String name = request.getParameter("name");
		String post = request.getParameter("positions");
		String account = request.getParameter("account");
		String pageSize = request.getParameter( "pagination[perPage]" );
		String pageNo = request.getParameter( "pagination[toPage]" );
		request.getParameterMap();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "NAME", name );
		map.put( "LOGIN_ID", account );
		map.put( "POST", post );
		Page<User> page = userService.findByPage(map, 
				pageSize == null ? 20 : Integer.parseInt( pageSize ), 
				pageNo == null ? 1 : Integer.parseInt( pageNo ) );
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( page ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void update( HttpServletRequest request,HttpServletResponse response ){
		String name = request.getParameter("name");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if( password != null && !"".equals( password ) ) 
			password = MD5.getInstance().getMD5Code( password );
		String sex = request.getParameter("gender");
		String positions = request.getParameter("positions");
		String active = request.getParameter("isValid");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		String groupId = request.getParameter("groupId");
		User user = new User();
		user.setGroupId( Long.parseLong( groupId ) );
		user.setId( Long.parseLong( id ) );
		user.setAccount( account );
		user.setEmail(email);
		user.setPassword(password);
		user.setGender( Integer.parseInt( sex ) );
		user.setName(name);
		user.setPositions( positions );
		user.setIsValid( Boolean.parseBoolean( active ) );
		int rnum = userService.update( user );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( rnum > 0 ){
			rm.setResult( true );
		} else {
			rm.setResult( false );
			rm.setMessage( "update user failed!" );
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( rm ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void getRoles( HttpServletRequest request,HttpServletResponse response ){
		String userId = request.getParameter( "id" );
		List<Role> list = userService.findRoles( Long.parseLong( userId ) );
		List<Map<String,Object>> rs = new ArrayList<Map<String,Object>>();
		for( Role r : list ){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put( "id", r.getId() );
			map.put( "name", r.getRoleName() );
			rs.add( map );
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( rs ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void delete( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter("id");
		int rnum = userService.delete( Long.parseLong( id ) );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( rnum > 0 ){
			rm.setResult( true );
		} else {
			rm.setResult( false );
			rm.setMessage( "save user failed!" );
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( rm ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}

	public void saveRoles( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter( "id" );
		String roleStr = request.getParameter( "roles" );
		String[] roleIds = roleStr.split( "," );
		Long[] roles = new Long[roleIds.length];
		for( int i = 0 ; i < roleIds.length ; i ++ ){
			if( !"".equals( roleIds[ i ] ) ){
				roles[ i ] = Long.parseLong( roleIds[ i ] );
			}
		}
		userService.updateUserRoles( roles, Long.parseLong( id ) );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		rm.setResult( true );
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( rm ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}	
}
