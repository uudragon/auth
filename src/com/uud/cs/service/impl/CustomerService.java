package com.uud.cs.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
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

	@Override
	public Page<Customer> findByPage(Map<String, Object> map, Integer pageNo,
			Integer pageSize) {
		if( pageNo == null ){
			pageNo = 1;
		}
		if( pageSize == null ){
			pageSize = 10; 
		}
		Page<Customer> page=new Page<Customer>();
		page.setPageSize( pageSize );
		page.setPageNo( pageNo );
		int count = customerDao.count( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize == 0 ? count / pageSize : count / pageSize + 1 );
		page.setRecords( customerDao.findByPage( map, pageSize, pageNo) );
		return page;
	}
	
	@Override
	public int allot(Map<String, Object> map) {
		return customerDao.allot( map );
	}
	
	@Override
	public Customer findByPhone( String phone ){
		return customerDao.findByPhone( phone );
	}
	
	public ICustomerDao getConsumerDao() {
		return customerDao;
	}

	public void setConsumerDao(ICustomerDao consumerDao) {
		this.customerDao = consumerDao;
	}
}
