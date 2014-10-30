package com.uud.cs.dao;

import java.util.Map;

import com.uud.cs.entity.Customer;

public interface ICustomerDao {
	public Long save( Map<String,Object> map );

	public Customer findByCode(String code);

	public int update(Map<String, Object> map);
}
