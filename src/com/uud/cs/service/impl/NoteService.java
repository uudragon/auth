package com.uud.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uud.auth.entity.Page;
import com.uud.cs.dao.INoteDao;
import com.uud.cs.entity.Note;
import com.uud.cs.entity.NoteInBox;
import com.uud.cs.entity.NoteOutBox;
import com.uud.cs.service.INoteService;

@Service("noteService")
public class NoteService implements INoteService {

	@Autowired
	@Qualifier("noteDao")
	private INoteDao noteDao;
	
	@Override
	public Long save(Map<String, Object> map) {
		Long noteId = noteDao.save( map );
		Map<String,Object> box = new HashMap<String,Object>();
		box.put( "noteId", noteId );
		box.put( "user", map.get("sender") );
		box.put( "status", NoteOutBox.STATUS_NOT_SEND );
		noteDao.saveOut( box );
		return noteId;
	}
	@Override
	public void saveOrUpdate(Map<String, Object> map) {
		if( map.containsKey( "boxId") && map.get( "boxId" ) != null
				&& map.containsKey( "id") && map.get( "id" ) != null){
			Map<String,Object> note = new HashMap<String,Object>();
			note.put( "id", map.get( "id" ) );
			note.put( "receiver", map.get( "receiver" ) );
			note.put( "content", map.get( "content" ) );
			noteDao.updateNote( map );
			
			Map<String,Object> out = new HashMap<String,Object>();
			out.put( "boxId", map.get( "boxId" ) );
			out.put( "status", NoteOutBox.STATUS_HAS_SEND );
			noteDao.updateOut( map );
			
			Map<String,Object> in = new HashMap<String,Object>();
			in.put( "noteId", map.get( "id" ) );
			in.put( "user", map.get("receiver") );
			noteDao.saveIn( in );
		} else {
			Long noteId = noteDao.save( map );
			
			Map<String,Object> box = new HashMap<String,Object>();
			box.put( "noteId", noteId );
			box.put( "user", map.get("sender") );
			box.put( "status", NoteOutBox.STATUS_HAS_SEND );
			noteDao.saveOut( box );
			
			Map<String,Object> in = new HashMap<String,Object>();
			in.put( "noteId", map.get( "id" ) );
			in.put( "user", map.get("receiver") );
			noteDao.saveIn( in );
		}
	}
	
	@Override
	public Page<Note> findInNotes( Map<String,Object> map, int pageSize, int pageNo ){
		Page<Note> page = new Page<Note>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		
		Integer count = noteDao.countIn( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize  == 0 ? count / pageSize : count / pageSize + 1 );
		
		List<Note> list = noteDao.findInNotes( map, pageSize, pageNo );
		page.setRecords( list );
		return page;
	}

	@Override
	public Page<Note> findOutNotes(Map<String, Object> map, int pageSize,
			int pageNo) {
		Page<Note> page = new Page<Note>();
		page.setPageNo( pageNo );
		page.setPageSize( pageSize );
		
		Integer count = noteDao.countOut( map );
		page.setRecordsCount( count );
		page.setPageNumber( count % pageSize  == 0 ? count / pageSize : count / pageSize + 1 );
		
		List<Note> list = noteDao.findOutNotes( map, pageSize, pageNo );
		page.setRecords( list );
		return page;
	}
	
	@Override
	public Integer deleteIn( Long boxId ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "boxId", boxId );
		map.put( "flag", true );
		return noteDao.updateIn( map );
	}

	@Override
	public Integer deleteOut( Long boxId ) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put( "boxId", boxId );
		map.put( "flag", true );
		return noteDao.updateOut( map );
	}
	
	@Override
	public Integer deleteAllIn( String user ){
		return noteDao.updateIn( user );
	}
	
	@Override
	public Integer deleteAllOut( String user ){
		return noteDao.updateOut( user );
	}
	
	@Override
	public Note readIn( Long boxId ){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boxId", boxId);
		map.put("status", NoteInBox.STATUS_HAS_READ );
		noteDao.updateOut( map );
		return noteDao.findByInId( boxId );
	}
	
	@Override
	public Note readOut( Long boxId ){
		return noteDao.findByOutId( boxId );
	}

	@Override
	public List<Note> findNotRead( String user ) {
		
		return noteDao.findNotRead( user );
	}
}
