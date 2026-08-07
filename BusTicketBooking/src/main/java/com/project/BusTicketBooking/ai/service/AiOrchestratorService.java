package com.project.BusTicketBooking.ai.service;

import org.springframework.stereotype.Service;

@Service
public class AiOrchestratorService {

    private final GeminiService geminiService;
    private final BusSearchAiService busSearchAiService;

    public AiOrchestratorService(
            GeminiService geminiService,
            BusSearchAiService busSearchAiService) {

        this.geminiService = geminiService;
        this.busSearchAiService = busSearchAiService;
    }

    public String process(String message) {

        String intent = geminiService.detectIntent(message);

        switch (intent) {

            case "GREETING":
                return """
                        👋 Welcome!

                        Available Features:
                        1. Book Ticket
                        2. Search Bus
                        3. Cancel Ticket
                        4. View Booking

                        Example:
                        Book one seat from Chennai to Madurai
                        """;

            case "BOOK_TICKET":
            case "SEARCH_BUS":

                String source =
                        geminiService.extractSource(message);

                String destination =
                        geminiService.extractDestination(message);

                return busSearchAiService.search(source, destination);

            case "CANCEL_TICKET":
                return "Please provide your Booking ID.";

            case "VIEW_BOOKING":
                return "Please provide your Booking ID.";

            default:
                return """
                        Sorry, I couldn't understand.

                        You can ask:

                        • Book Ticket
                        • Search Bus
                        • Cancel Ticket
                        • View Booking
                        """;
        }
    }
}