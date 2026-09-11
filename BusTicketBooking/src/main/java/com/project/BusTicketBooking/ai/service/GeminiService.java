package com.project.BusTicketBooking.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.ai.prompt.AgentPrompt;
import com.project.BusTicketBooking.dto.ai.IntentDetailsDTO;

@Service
public class GeminiService {

    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public IntentDetailsDTO understand(String message) {

        String prompt = """
                Analyze the user's bus booking request.

                Return structured information.

                Allowed intents:

                GREETING
                BOOK_TICKET
                SEARCH_BUS
                CANCEL_TICKET
                VIEW_BOOKING
                SEAT_AVAILABILITY
                PAYMENT_STATUS
                UNKNOWN

                Extract:

                source
                destination
                numberOfSeats
                travelDate
                bookingId

                Important:
                - If a value is missing, return null.
                - If the user gives a date such as tomorrow,
                  convert it to YYYY-MM-DD when possible.
                - Return only structurSed information.

                User:
                """ + message;

        return chatClient.prompt()
                .system(AgentPrompt.SYSTEM_PROMPT)
                .user(prompt)
                .call()
                .entity(IntentDetailsDTO.class);
    }
}