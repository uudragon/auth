package com.uud.cs.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.IMessageDao;
import com.uud.cs.entity.Message;
import com.uud.cs.entity.MessageTemplate;

@Repository("messageDao")
public class MessageDao extends SqlMapClientDaoSupport implements IMessageDao {

	@Autowired
	public MessageDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient( sqlMapClient );
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "message.save", map );
	}

	@Override
	public Long saveTemplate(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "message.saveTemplate", map );
	}

	@Override
	public Integer count(Map<String, Object> map) {
		
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "message.countByParams", map );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findByParams(Map<String, Object> map, int pageSize, int pageNo ) {
		
		return this.getSqlMapClientTemplate()
				.queryForList( "message.findByParams", map, ( pageNo - 1 ) * pageSize, pageSize   );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageTemplate> findTemplate() {
		return this.getSqlMapClientTemplate().queryForList("message.findTemplate");
	}

}
