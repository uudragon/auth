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
import com.uud.auth.entity.UserGroup;
import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.service.IUserGroupService;

@Controller("userGroupAction")
public class UserGroupAction {
	
	@Autowired
	@Qualifier("userGroupService")
	private IUserGroupService userGroupService;
	
	public void findById( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter("id");
		UserGroup group = userGroupService.findById( Long.parseLong( id ) );
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( group ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void search( HttpServletRequest request,HttpServletResponse response ){
		String pageSize = request.getParameter( "pagination[perPage]" );
		String pageNo = request.getParameter( "pagination[toPage]" );
		String name = request.getParameter("name");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "GROUP_NAME", name );
		
		Page<UserGroup> page = userGroupService.search(map, 
				pageSize != null ? Integer.parseInt( pageSize ) : 20 ,
				pageNo != null ? Integer.parseInt( pageNo ) : 1 );
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
	
	public void save( HttpServletRequest request,HttpServletResponse response ){
		String name = request.getParameter("name");
		UserGroup ug = new UserGroup();
		ug.setName( name );
		Long id = userGroupService.save( ug );
		
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
	
	public void update( HttpServletRequest request,HttpServletResponse response ){
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		UserGroup ug = new UserGroup();
		ug.setName( name );
		ug.setId( Long.parseLong( id ) );
		int result = userGroupService.update( ug );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( result > 0 ){
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
	
	public void delete( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter( "id" );
		int result = userGroupService.delete( Long.parseLong( id ) );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( result > 0 ){
			rm.setResult( true );
		} else {
			rm.setResult( false );
			rm.setMessage( "delete user failed!" );
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
	
	public void getGroups( HttpServletRequest request,HttpServletResponse response ){
		List<UserGroup> list = userGroupService.findAll();
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( list ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void getRoles( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter("id");
		List<Role> list = userGroupService.findRoles( Long.parseLong( id ) );
		PrintWriter writer = null;
		List<Map<String,Object>> rs = new ArrayList<Map<String,Object>>();
		for( Role r : list ){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put( "id", r.getId() );
			map.put( "name", r.getRoleName() );
			rs.add( map );
		}
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
		userGroupService.updateUserRoles( roles, Long.parseLong( id ) );
		
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
	
	public IUserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

}
