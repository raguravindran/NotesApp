package com.rs.notes.thread.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.notes.thread.components.Conversation;
import com.rs.notes.thread.components.repository.ConversationRepo;

@RestController
public class ConversationController {

	@Autowired
	ConversationRepo convRepo;

	/**
	 * This is a sample api
	 * @param id
	 * @return
	 */
	@RequestMapping("/api/sample")
	public List<Conversation> getSampleResponse(@RequestParam int id) {
		List<Conversation> conv = convRepo.findByConversationId(id);
		return conv;
	}

	/**
	 * Add a new conversation, request params to have all required
	 * @param conv
	 * @return
	 */
	@RequestMapping("/api/addConversation")
	public String insertConvo(Conversation conv) {
		convRepo.save(conv);
		return "inserted";
	}

	/**
	 * get conversations based on saved boolean property
	 * @param saved - true/false
	 * @return - List of Conversation POJO
	 */
	@RequestMapping("/api/getConversations")
	public List<Conversation> insertConvo(@RequestParam String saved) {
		List<Conversation> convList = convRepo.findBySaved(Boolean.parseBoolean(saved));
		return convList;
	}
}
