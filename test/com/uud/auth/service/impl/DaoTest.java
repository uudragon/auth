package com.uud.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.uud.auth.dao.IResourceDao;
import com.uud.auth.dao.IRoleDao;
import com.uud.auth.dao.IRoleGroupDao;
import com.uud.auth.dao.IUserDao;
import com.uud.auth.dao.IUserGroupDao;
import com.uud.auth.dao.impl.RoleDao;
import com.uud.auth.dao.impl.RoleGroupDao;
import com.uud.auth.entity.Resource;
import com.uud.auth.entity.ResourceRole;
import com.uud.auth.entity.ResourceRoleGroup;
import com.uud.auth.entity.Role;
import com.uud.auth.entity.RoleGroup;
import com.uud.auth.entity.User;
import com.uud.auth.entity.UserGroup;

public class DaoTest {

	public static void main(String[] args) {
		
		ApplicationContext ctx=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		RoleDao dao = (RoleDao) ctx.getBean( "roleDao" );
		System.out.println( JSON.toJSONString( dao.findRolePermissions() ) );
		/*RoleGroupDao dao = (RoleGroupDao) ctx.getBean("roleGroupDao");
		List<ResourceRoleGroup> resources = new ArrayList<ResourceRoleGroup>();
		ResourceRoleGroup rrg=new ResourceRoleGroup();
		RoleGroup role= new RoleGroup();
		role.setId(1l);
		rrg.setRg(role);
		Resource res = new Resource();
		res.setId(1l);
		rrg.setResource( res );
		resources.add( rrg );
		dao.addResource( resources );*/
		//IResourceDao dao = (IResourceDao) ctx.getBean("resourceDao");
		/*Resource resource = new Resource();
		resource.setMethod("get");
		resource.setActive(false);
		resource.setName("test");
		resource.setParent(0l);
		resource.setUrl("/test/test");
		dao.save(resource);
		System.out.println( dao.findById(1l) );
		resource = new Resource();
		resource.setName("123");
		System.out.println( dao.findByParams( resource ) );
		resource.setName("te");
		System.out.println( dao.findByParams( resource ) );
		System.out.println( dao.countByParams( resource ) );*/
	/*	Resource  resource = new Resource();
		resource.setId( 1l );
		resource.setMethod("post");
		resource.setActive(true);
		resource.setName("test123");
		resource.setParent(0l);
		resource.setUrl("/test1/test");
		dao.update(resource);*/
/*		IUserGroupDao dao = (IUserGroupDao) ctx.getBean("userGroupDao");
		List<UserGroup> roles = new ArrayList<UserGroup>();
		UserGroup ug = new UserGroup();
		ug.setId(1l);
		ug.setRoleId(1l);
		roles.add(ug);
		dao.addRoles(roles);*/
		/*IUserDao dao = (IUserDao) ctx.getBean("userDao");
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setId(1l);
		user.setRoleId(1l);
		users.add( user );
		dao.addRoles(users);*/
		/*IRoleGroupDao dao = (IRoleGroupDao) ctx.getBean("roleGroupDao");
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setId(1l);
		role.setGroupId( 1l );
		roles.add( role );
		dao.addRole(roles);*/
		/*RoleGroup rg = new RoleGroup();
		rg.setGroupCode("test");
		rg.setGroupName("test");
		rg.setParentCode("test");
		rg.setIsRemoved(false);
		dao.save( rg );
		System.out.println( JSON.toJSONString( dao.findById(1l) ) );
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ROLE_NAME", "t");
		System.out.println( JSON.toJSONString( dao.findByParams( map ) ) );*/
		/*RoleGroup rg = new RoleGroup();
		rg.setGroupCode("test1");
		rg.setGroupName("test1");
		rg.setParentCode("test1");
		rg.setIsRemoved(true);
		rg.setId( 1l );
		dao.update(rg);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ROLE_NAME", "t");
		System.out.println(dao.countByParams(map));*/
		//
		//IRoleDao dao = (IRoleDao) ctx.getBean("roleDao");
		/*Role role = new Role();
		role.setIsRemoved( false );
		role.setRoleName("test");
		dao.save(role);
		role.setRoleName("test21");
		dao.update(role);
		*/
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("ROLE_NAME", "t");
		System.out.println( JSON.toJSONString(dao.findById(1l)));*/
		
		
		//map.put("GROUP_NAME", "th");
		
		//System.out.println( JSON.toJSONString( dao.findByParams(map) ) );
		//System.out.println( JSON.toJSONString( dao.countByParams( map ) ) );
		//System.out.println( JSON.toJSONString( dao.countByGroupId( 1l ) ) );
		//System.out.println( JSON.toJSONString( dao.delete( 1l ) ) );
		/*User user = new User();
		user.setId(1l);
		user.setName( "test" );
		user.setPassword( "tetewq" ) ;
		user.setAccount("fdsafd");
		System.out.println( JSON.toJSONString( dao.update( user ) ) );*/
		/*IUserGroupDao ugdao = (IUserGroupDao) ctx.getBean("userGroupDao");
		UserGroup ug = new UserGroup();
		ug.setId( 1l );
		ug.setGroupName( "test" );
		ug.setGroupCode( "test " );
		ug.setIsRemoved( true );
		ugdao.update( ug );
		//System.out.println( JSON.toJSONString(ugdao.findById(2l)) );;
		ugdao.removeUser( 1l, 1l );*/
	}

}
