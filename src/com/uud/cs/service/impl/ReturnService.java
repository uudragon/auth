package com.uud.cs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
import com.uud.cs.dao.IReturnDao;
import com.uud.cs.entity.ReturnGoodsForm;
import com.uud.cs.service.IReturnService;

@Service("returnService")
public class ReturnService implements IReturnService {

	@Autowired
	@Qualifier("returnDao")
	private IReturnDao returnDao;
	
	@Override
	public Page<ReturnGoodsForm> findByPage( Map<String, Object> map, Integer pageNo, Integer pageSize ) {
		Page<ReturnGoodsForm> page = new Page<ReturnGoodsForm>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		Integer count = returnDao.countByParams( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize == 0 ? count / pageSize : count / pageSize + 1 );
		List<ReturnGoodsForm> records = returnDao.findByPage( map, pageNo, pageSize );
		page.setRecords( records );
		return page;
	}
	
	public IReturnDao getReturnDao() {
		return returnDao;
	}
	
	public void setReturnDao(IReturnDao returnDao) {
		this.returnDao = returnDao;
	}
}
