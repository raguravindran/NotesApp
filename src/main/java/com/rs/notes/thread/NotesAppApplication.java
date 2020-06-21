package com.rs.notes.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.rs.notes.thread.components.Conversation;

@SpringBootApplication
public class NotesAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NotesAppApplication.class, args);
		
//		Conversation conv = context.getBean(Conversation.class);
//		conv.show();
	}

}
