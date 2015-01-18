package com.uud.cs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
import com.uud.cs.dao.IExchangeDao;
import com.uud.cs.entity.ExchageGoodsForm;
import com.uud.cs.service.IExchangeService;

@Service("exchangeService")
public class ExchangeService implements IExchangeService {

	@Qualifier("exchangeDao")
	@Autowired
	private IExchangeDao exchangeDao;
	
	@Override
	public void save(Map<String, Object> map) {
		exchangeDao.save( map );
	}

	@Override
	public Page<ExchageGoodsForm> findByPage(Map<String, Object> map,
			Integer pageSize, Integer pageNo) {
		Page<ExchageGoodsForm> page = new Page<ExchageGoodsForm>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		Integer count = exchangeDao.countByPage( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize == 0 ? count / pageSize : count / pageSize + 1 );
		List<ExchageGoodsForm> records = exchangeDao.findByPage( map,pageSize, pageNo );
		page.setRecords( records );
		return page;
	}
	
	@Override
	public void updateStatus( Map<String,Object> map ){
		exchangeDao.updateStatus( map );
	}
	
	public IExchangeDao getExchangeDao() {
		return exchangeDao;
	}

	public void setExchangeDao(IExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}
}
