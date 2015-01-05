package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.ReturnGoodsForm;

public interface IReturnDao {
	public void save( Map<String,Object> map );

	public Integer countByParams(Map<String, Object> map);

	public List<ReturnGoodsForm> findByPage(Map<String, Object> map, Integer pageNo,
			Integer pageSize);
}
