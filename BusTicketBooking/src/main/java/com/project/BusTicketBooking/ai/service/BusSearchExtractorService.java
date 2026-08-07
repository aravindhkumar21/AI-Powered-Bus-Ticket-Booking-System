package com.project.BusTicketBooking.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.ai.BusSearchRequest;

@Service
public class BusSearchExtractorService {

    private final ChatClient chatClient;

    public BusSearchExtractorService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public BusSearchRequest extract(String message) {

        String prompt = """
                Extract the source and destination.

                Return ONLY JSON.

                Example:

                {
                  "source":"Chennai",
                  "destination":"Madurai"
                }

                User:
                """ + message;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(BusSearchRequest.class);
    }
}