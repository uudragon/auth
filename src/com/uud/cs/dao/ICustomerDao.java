package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.Customer;

public interface ICustomerDao {
	public Long save( Map<String,Object> map );

	public Customer findByCode(String code);

	public int update(Map<String, Object> map);
	
	public List<Customer> findByPage( Map<String,Object> map, Integer pageSize, Integer pageNo );
	
	public Integer count( Map<String,Object> map );

	public Integer allot(Map<String, Object> map);

	public Customer findByPhone( String phone );

	public Customer findByName( String name );
}
