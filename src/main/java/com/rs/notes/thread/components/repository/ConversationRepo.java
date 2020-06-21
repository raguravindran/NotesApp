package com.rs.notes.thread.components.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rs.notes.thread.components.Conversation;

public interface ConversationRepo extends JpaRepository<Conversation, Integer> {
	List<Conversation> findByConversationId(int conversationId);
	List<Conversation> findBySaved(boolean saved);

}
