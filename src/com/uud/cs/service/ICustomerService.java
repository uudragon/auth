package com.uud.cs.service;

import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.Customer;

public interface ICustomerService {
	
	public Customer findByCode( String code );
	
	public Page<Customer> findByPage( Map<String,Object> map ,Integer pageNo , Integer pageSize );
	
	public int allot( Map<String,Object> map );
}
