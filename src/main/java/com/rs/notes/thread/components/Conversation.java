package com.rs.notes.thread.components;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "conversation")
public class Conversation {

	public int getCoversationId() {
		return conversationId;
	}
	public void setCoversationId(int conversationId) {
		this.conversationId = conversationId;
	}
	public String getMessge() {
		return messge;
	}
	public void setMessge(String messge) {
		this.messge = messge;
	}
	public boolean isSaved() {
		return saved;
	}
	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Id
	@Column(name = "conversation_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int conversationId;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "message")
	private String messge;
	@Column(name = "saved")
	private boolean saved;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;



}
