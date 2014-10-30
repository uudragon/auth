package com.uud.cs.service;

import com.uud.cs.entity.Customer;

public interface ICustomerService {
	public Customer findByCode( String code );
}
