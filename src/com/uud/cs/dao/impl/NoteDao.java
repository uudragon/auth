package com.uud.cs.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.uud.cs.dao.INoteDao;
import com.uud.cs.entity.Note;
import com.uud.cs.entity.NoteInBox;

@Repository("noteDao")
public class NoteDao extends SqlMapClientDaoSupport implements INoteDao {

	@Autowired
	public NoteDao( @Qualifier("sqlMapClient") SqlMapClient sqlMapClient ){
		super();
		super.setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Long save(Map<String, Object> map) {
		
		return (Long) this.getSqlMapClientTemplate().insert( "note.save", map );
	}
	
	@Override
	public Long saveIn(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "note.saveIn", map );
	}
	
	@Override
	public Long saveOut(Map<String, Object> map) {
		return (Long) this.getSqlMapClientTemplate().insert( "note.saveOut", map );
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findInNotes( Map<String,Object> params, int pageSize, int pageNo ){
		return this.getSqlMapClientTemplate()
				.queryForList( "note.findInNotes", params, ( pageNo - 1 ) * pageSize, pageSize  );
	}

	@Override
	public Integer countIn(Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "note.countInByParams", map );
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Note> findOutNotes( Map<String,Object> params, int pageSize, int pageNo ){
		return this.getSqlMapClientTemplate()
				.queryForList( "note.findOutNotes", params, ( pageNo - 1 ) * pageSize, pageSize  );
	}

	@Override
	public Integer countOut(Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject( "note.countOutByParams", map );
	}

	@Override
	public Integer updateIn( Map<String,Object> map ) {
		return this.getSqlMapClientTemplate().update( "note.updateIn", map );
	}
	
	@Override
	public Integer updateOut( Map<String,Object> map ){
		return this.getSqlMapClientTemplate().update( "note.updateOut", map );
	}
	
	@Override
	public Integer updateIn( String user ){
		return this.getSqlMapClientTemplate().update( "note.updateAllIn", user );
	}
	
	@Override
	public Integer updateOut( String user ){
		return this.getSqlMapClientTemplate().update( "note.updateAllOut", user );
	}
	
	@Override
	public Note findById( Long id ){
		return (Note) this.getSqlMapClientTemplate().queryForObject( "note.findById", id );
	}

	@Override
	public Note findByInId(Long id) {
		return (Note) this.getSqlMapClientTemplate().queryForObject( "note.findByInBoxId", id );
	}

	@Override
	public Note findByOutId(Long id) {
		return (Note) this.getSqlMapClientTemplate().queryForObject( "note.findByOutBoxId", id );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Note> findNotRead( String user ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", NoteInBox.STATUS_NOT_READ );
		map.put("user", user);
		return this.getSqlMapClientTemplate().queryForList( "note.findInNotes", map);
	}
	
	@Override
	public int updateNote( Map<String,Object> map ){
		return this.getSqlMapClientTemplate().update( "note.updateNote", map );
	}
}
