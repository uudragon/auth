package com.uud.cs.service;

import java.util.List;
import java.util.Map;

import com.uud.auth.entity.Page;
import com.uud.cs.entity.Note;

public interface INoteService {
	public Long save( Map<String,Object> map );

	public Page<Note> findInNotes(Map<String, Object> map, int pageSize, int pageNo);
	
	public Page<Note> findOutNotes(Map<String, Object> map, int pageSize, int pageNo);

	public Integer deleteIn( Long boxId );
	
	public Integer deleteOut( Long boxId );

	public Integer deleteAllIn(String user);

	public Integer deleteAllOut(String user);

	public Note readIn(Long id);
	
	public Note readOut(Long id);
	
	public List<Note> findNotRead( String user );

	public void saveOrUpdate(Map<String, Object> map);
}
