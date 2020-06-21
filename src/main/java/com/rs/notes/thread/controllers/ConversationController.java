package com.rs.notes.thread.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.notes.thread.components.Conversation;
import com.rs.notes.thread.components.repository.ConversationRepo;

@RestController
public class ConversationController {

	@Autowired
	ConversationRepo convRepo;
	
	@RequestMapping("sample")
	public String getSampleResponse(Conversation conv) {
//		Conversation conv = new Conversation();
//		conv.show();
//		String s = "this is working" + conv.userId;
		Iterable<Conversation> conversation = convRepo.findAll();
		conversation.forEach(conver -> {
			System.out.println(conver.getMessge());
		});
		return "this is working";
	}

	@RequestMapping("/addConversation")
	public String insertConvo(Conversation conv) {
		convRepo.save(conv);
		return "inserted";
	}
}
