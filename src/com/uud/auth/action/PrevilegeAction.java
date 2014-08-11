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
import com.uud.auth.entity.Resource;
import com.uud.auth.impls.info.AccessFactory;
import com.uud.auth.service.IResourceService;

@Controller("privilege")
public class PrevilegeAction {
	
	@Autowired
	@Qualifier("resourceService")
	private IResourceService resourceService; 
	
	public void load( HttpServletRequest request,HttpServletResponse response ){
		
		List<Map<String,Object>> tree = new ArrayList<Map<String,Object>>();
		List<Resource> list = resourceService.findAll();
		for( Resource r : list ){
			Map<String,Object> node = new HashMap<String,Object>();
			node.put( "id", r.getId() );
			node.put( "pId", r.getParent() );
			node.put( "name", r.getName() );
			node.put( "code", r.getCode() );
			node.put( "link", r.getUrl() );
			node.put( "method", r.getMethod() );
			if( r.getParent() == 0 ){
				node.put( "open",true );
			}
			tree.add( node );
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( tree ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
		
	}
	
	public void save( HttpServletRequest request,HttpServletResponse response ){
		String pid = request.getParameter("pId");
		String method = request.getParameter( "method" );
		String name = request.getParameter( "name" );
		String code = request.getParameter( "code" );
		String root = request.getParameter("isRoot");
		String url = request.getParameter( "link" );
		Resource resource = new Resource();
		resource.setMethod( method );
		resource.setName( name );
		resource.setParent( Boolean.parseBoolean( root ) ? 0 : Long.parseLong( pid ) );
		resource.setUrl( url );
		resource.setCode( code );
		resource.setActive( true );
		Long id = resourceService.save( resource );
		
		AccessFactory.getAccessInfo().load();
		
		Resource r = resourceService.findById( id );
		Map<String,Object> node = new HashMap<String,Object>();
		node.put( "id", r.getId() );
		node.put( "pId", r.getParent() );
		node.put( "name", r.getName() );
		node.put( "code", r.getCode() );
		node.put( "link", r.getUrl() );
		node.put( "method", r.getMethod() );
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write( JSON.toJSONString( node ) );
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( writer != null ){
				writer.close();
			}
		}
	}
	
	public void update( HttpServletRequest request,HttpServletResponse response ){
		String method = request.getParameter( "method" );
		String code = request.getParameter( "code" );
		String name = request.getParameter( "name" );
		String url = request.getParameter( "link" );
		String id = request.getParameter( "id" );
		Resource resource = new Resource();
		resource.setId( Long.parseLong( id ) );
		resource.setMethod( method );
		resource.setCode( code );
		resource.setName( name );
		resource.setUrl( url );
		resource.setActive( true );
		int r = resourceService.update( resource );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( r > 0 ){
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
	
	public void delete( HttpServletRequest request,HttpServletResponse response ){
		String id = request.getParameter("id");
		int r = resourceService.delete( Long.parseLong( id ) );
		
		AccessFactory.getAccessInfo().load();
		
		ResponseMessage rm = new ResponseMessage();
		if( r > 0 ){
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
	
	public IResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}
}
