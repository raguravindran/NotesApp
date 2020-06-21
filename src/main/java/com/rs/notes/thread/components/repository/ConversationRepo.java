package com.rs.notes.thread.components.repository;

import org.springframework.data.repository.CrudRepository;

import com.rs.notes.thread.components.Conversation;

public interface ConversationRepo extends CrudRepository<Conversation, Integer> {
	

}
