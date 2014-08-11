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
import com.uud.auth.entity.RoleGroup;
import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.service.IRoleGroupService;


@Controller("roleGroupAction")
public class RoleGroupAction {
	@Autowired
	@Qualifier("rgService")
	private IRoleGroupService rgService;

	public void save( HttpServletRequest request,HttpServletResponse response ){
		
		String name = request.getParameter("name");
		RoleGroup rg = new RoleGroup();
		rg.setName( name );
		Long id = rgService.save( rg );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( id != null ){
			rm.setResult( true );
		} else {
			rm.setResult( false );
			rm.setMessage( "save role_group failed!" );
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
		rgService.updateResource( privileges, Long.parseLong( id ) );
		
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
	
	public void search( HttpServletRequest request,HttpServletResponse response ){
		
		String pageSize = request.getParameter( "pagination[perPage]" );
		String pageNo = request.getParameter( "pagination[toPage]" );
		String name = request.getParameter("groupName");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("GROUP_NAME", name);
		Page<RoleGroup> page = rgService.findByPage(map, 
				pageSize == null ? 20 : Integer.parseInt( pageSize ) ,
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		RoleGroup rg = new RoleGroup();
		rg.setName( name );
		rg.setId( Long.parseLong( id ) );
		int result = rgService.update( rg );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( result > 0 ){
			rm.setResult( true );
		} else {
			rm.setResult( false );
			rm.setMessage( "update role_group failed!" );
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
		String id = request.getParameter("id");
		int result = rgService.delete( Long.parseLong( id ) );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( result > 0 ){
			rm.setResult( true );
		} else {
			rm.setResult( false );
			rm.setMessage( "update role_group failed!" );
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
		List<RoleGroup> list = rgService.findAll();
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
	
	public void getPrivilege( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter( "id" );
		List<Resource> list = rgService.findResources( Long.parseLong( id ) );
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
	
	public IRoleGroupService getRgService() {
		return rgService;
	}

	public void setRgService(IRoleGroupService rgService) {
		this.rgService = rgService;
	}
	
	
	
}
