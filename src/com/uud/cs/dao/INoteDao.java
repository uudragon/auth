package com.uud.cs.dao;

import java.util.List;
import java.util.Map;

import com.uud.cs.entity.Note;

public interface INoteDao {
	public Long save( Map<String,Object> map );

	public List<Note> findInNotes( Map<String, Object> params, int pageSize, int pageNo );
	
	public Integer countIn( Map<String,Object> map );

	public Long saveIn(Map<String, Object> map);

	public Long saveOut(Map<String, Object> map);

	public Integer countOut(Map<String, Object> map);

	public List<Note> findOutNotes(Map<String, Object> params, int pageSize, int pageNo);

	public Integer updateIn(Map<String, Object> map);

	public Integer updateOut(Map<String, Object> map);

	public Integer updateIn( String user );

	public Integer updateOut(String user);

	public Note findById(Long id);
	
	public Note findByInId( Long id );
	
	public Note findByOutId( Long id );

	public List<Note> findNotRead(String user);

	public int updateNote(Map<String, Object> map);

}
