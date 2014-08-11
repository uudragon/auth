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
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.Role;
import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.service.IRoleService;

@Controller("roleAction")
public class RoleAction {
	
	@Autowired
	@Qualifier("roleService")
	private IRoleService roleService;

	public void save( HttpServletRequest request,HttpServletResponse response ){
		request.getParameterMap();
		String name = request.getParameter("roleName");
		String groupId = request.getParameter("groupId");
		Role role = new Role();
		role.setGroupId( Long.parseLong( groupId ) );
		role.setRoleName( name );
		Long id = roleService.save( role );
		
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
		String name = request.getParameter("roleName");
		String id = request.getParameter( "id" );
		String groupId = request.getParameter( "groupId" );
		Role role = new Role();
		role.setRoleName( name );
		role.setGroupId( Long.parseLong( groupId ) );
		role.setId( Long.parseLong( id ) );
		int result = roleService.update( role );
		
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
	
	public void search( HttpServletRequest request,HttpServletResponse response ){
		String name = request.getParameter("roleName");
		String pageSize = request.getParameter( "pagination[perPage]" );
		String pageNo = request.getParameter( "pagination[toPage]" );
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "ROLE_NAME", name );
		Page<Role> page = roleService.findByParams(map, 
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
	
	public void delete( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter( "id" );
		int result = roleService.delete( Long.parseLong( id ) );
		
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
	
	public void getAllRoles( HttpServletRequest request,HttpServletResponse response ){
		List<Map<String,Object>> rs = new ArrayList<Map<String,Object>>();
		List<Role> list = roleService.findAll();
		for( Role role : list ){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", role.getId());
			map.put("name", role.getRoleName());
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
	
	public void getPrivilege( HttpServletRequest request,HttpServletResponse response ){
		String roleId = request.getParameter( "id" );
		List<Resource> list = roleService.findResources( Long.parseLong( roleId ) );
		List<Map<String,Object>> resources = new ArrayList<Map<String,Object>>();
		for( Resource r : list ){
			Map<String,Object> node = new HashMap<String,Object>();
			node.put( "id", r.getId() );
			node.put( "pId", r.getParent() );
			node.put( "name", r.getName() );
			node.put( "url", r.getUrl() );
			node.put( "other", r.getMethod() );
			if( r.getParent() == 0 ){
				node.put( "open",true );
			}
			resources.add( node );
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( resources ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void savePrivileges( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter("id");
		String privilegeStr = request.getParameter("privileges");
		String[] privilegeIds = privilegeStr.split( "," );
		Long[] privileges = new Long[privilegeIds.length];
		for( int i = 0 ; i < privilegeIds.length ; i ++ ){
			if( !"".equals( privilegeIds[ i ] ) ){
				privileges[ i ] = Long.parseLong( privilegeIds[ i ] );
			}
		}
		roleService.updateResource( privileges, Long.parseLong( id ) );
		
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
	
	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
}
