package com.project.BusTicketBooking.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

	private final ChatClient chatClient;

	public AIServiceImpl(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	@Override
	public String chat(String message) {
		// TODO Auto-generated method stub

		return chatClient.prompt().user(message).call().content();
		
	}

}
