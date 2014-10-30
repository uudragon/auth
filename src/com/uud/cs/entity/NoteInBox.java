package com.uud.cs.entity;


public class NoteInBox {
	
	public static final boolean FLAG_NOT_DELETED = false;
	public static final boolean FLAG_DELETED = true;
	
	public static final byte STATUS_NOT_READ = 0;
	public static final byte STATUS_HAS_READ = 1;
	
	private Long id;
	private String user;
	private Boolean flag;
	private Byte status;
	private Long noteId;
	
	private Note note;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
}
