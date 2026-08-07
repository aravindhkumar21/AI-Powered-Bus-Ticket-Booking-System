package com.project.BusTicketBooking.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.ai.prompt.GreetingPrompt;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String detectIntent(String message) {

        String prompt = """
                Identify the user's intent.

                Return ONLY ONE of these values:

                SEARCH_BUS
                BOOK_TICKET
                CANCEL_TICKET
                VIEW_BOOKING
                GREETING
                UNKNOWN

                User:
                """ + message;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content()
                .trim();
    }

    public String chat(String message) {

        return chatClient.prompt()
                .system(GreetingPrompt.SYSTEM_PROMPT)
                .user(message)
                .call()
                .content();
    }
    
    public String extractSource(String message) {
        String prompt = """
                Extract ONLY the source city.

                User:
                """ + message;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content()
                .trim();
    }

    public String extractDestination(String message) {
        String prompt = """
                Extract ONLY the destination city.

                User:
                """ + message;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content()
                .trim();
    }

}