package com.uud.cs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.cs.dao.ICustomerDao;
import com.uud.cs.entity.Customer;
import com.uud.cs.service.ICustomerService;

@Service("customerService")
public class CustomerService implements ICustomerService{

	@Autowired
	@Qualifier("customerDao")
	private ICustomerDao customerDao;
	
	@Override
	public Customer findByCode(String code) {
		return customerDao.findByCode( code );
	}

	public ICustomerDao getConsumerDao() {
		return customerDao;
	}

	public void setConsumerDao(ICustomerDao consumerDao) {
		this.customerDao = consumerDao;
	}

}
