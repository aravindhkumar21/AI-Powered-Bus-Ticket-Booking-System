package com.project.BusTicketBooking.ai.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.ai.memory.ConversationMemory;
import com.project.BusTicketBooking.ai.memory.MemoryManager;
import com.project.BusTicketBooking.ai.tool.BookingTool;
import com.project.BusTicketBooking.ai.tool.BusSearchTool;
import com.project.BusTicketBooking.ai.tool.CancellationTool;
import com.project.BusTicketBooking.ai.tool.SeatTool;
import com.project.BusTicketBooking.ai.tool.ViewBookingTool;
import com.project.BusTicketBooking.dto.ai.BusSearchResponse;
import com.project.BusTicketBooking.dto.ai.IntentDetailsDTO;
import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.dto.seat.SeatResponseDTO;

@Service
public class AiOrchestratorService {

    private final GeminiService geminiService;
    private final MemoryManager memoryManager;

    private final BusSearchTool busSearchTool;
    private final SeatTool seatTool;
    private final BookingTool bookingTool;
    private final CancellationTool cancellationTool;
    private final ViewBookingTool viewBookingTool;

    public AiOrchestratorService(
            GeminiService geminiService,
            MemoryManager memoryManager,
            BusSearchTool busSearchTool,
            SeatTool seatTool,
            BookingTool bookingTool,
            CancellationTool cancellationTool,
            ViewBookingTool viewBookingTool) {

        this.geminiService = geminiService;
        this.memoryManager = memoryManager;
        this.busSearchTool = busSearchTool;
        this.seatTool = seatTool;
        this.bookingTool = bookingTool;
        this.cancellationTool = cancellationTool;
        this.viewBookingTool = viewBookingTool;
    }

    public String process(
            String sessionId,
            Long userId,
            String message) {

        ConversationMemory memory =
                memoryManager.getMemory(sessionId);

        memory.setUserId(userId);

        /*
         * Continue an existing conversation first.
         */
        if (memory.getStep() != null) {

            String result =
                    continueFlow(memory, message);

            if (result != null) {
                return result;
            }
        }

        /*
         * New request.
         */
        IntentDetailsDTO details =
                geminiService.understand(message);

        if (details == null ||
                details.getIntent() == null) {

            return unknown();
        }

        String intent =
                details.getIntent()
                        .trim()
                        .toUpperCase();

        memory.setCurrentIntent(intent);

        switch (intent) {

            case "GREETING":
                return greeting();

            case "BOOK_TICKET":
                return startBooking(memory, details);

            case "SEARCH_BUS":
                return searchBus(memory, details);

            case "CANCEL_TICKET":

                if (details.getBookingId() != null) {

                    return cancelBooking(
                            memory,
                            details.getBookingId()
                    );
                }

                memory.setStep(
                        "WAITING_FOR_CANCEL_BOOKING_ID"
                );

                return "Please provide your Booking ID.";

            case "VIEW_BOOKING":

                if (details.getBookingId() != null) {

                    return viewBooking(
                            memory,
                            details.getBookingId()
                    );
                }

                memory.setStep(
                        "WAITING_FOR_VIEW_BOOKING_ID"
                );

                return "Please provide your Booking ID.";

            case "SEAT_AVAILABILITY":

                return "Please provide the bus number or search a route first.";

            case "PAYMENT_STATUS":

                return "Please provide the Payment ID.";

            default:
                return unknown();
        }
    }

    // =========================================================
    // BOOKING
    // =========================================================

    private String startBooking(
            ConversationMemory memory,
            IntentDetailsDTO details) {

        memory.setCurrentIntent("BOOK_TICKET");

        if (details.getSource() != null) {
            memory.setSource(
                    details.getSource().trim()
            );
        }

        if (details.getDestination() != null) {
            memory.setDestination(
                    details.getDestination().trim()
            );
        }

        if (details.getNumberOfSeats() != null) {
            memory.setNumberOfSeats(
                    details.getNumberOfSeats()
            );
        }

        if (details.getTravelDate() != null) {
            memory.setTravelDate(
                    details.getTravelDate()
            );
        }

        if (memory.getSource() == null) {

            memory.setStep(
                    "WAITING_FOR_SOURCE"
            );

            return "Where are you travelling from?";
        }

        if (memory.getDestination() == null) {

            memory.setStep(
                    "WAITING_FOR_DESTINATION"
            );

            return "What is your destination?";
        }

        return searchForBooking(memory);
    }

    private String searchForBooking(
            ConversationMemory memory) {

        List<BusSearchResponse> buses =
                busSearchTool.search(
                        memory.getSource(),
                        memory.getDestination()
                );

        memory.setBuses(buses);

        memory.setStep(
                "WAITING_FOR_BUS_SELECTION"
        );

        return busSearchTool.format(
                buses,
                memory.getSource(),
                memory.getDestination()
        );
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private String searchBus(
            ConversationMemory memory,
            IntentDetailsDTO details) {

        if (details.getSource() == null ||
                details.getDestination() == null) {

            if (details.getSource() == null) {

                memory.setStep(
                        "WAITING_FOR_SOURCE"
                );

                return "Please provide the source city.";
            }

            memory.setSource(
                    details.getSource()
            );

            memory.setStep(
                    "WAITING_FOR_DESTINATION"
            );

            return "Please provide the destination city.";
        }

        List<BusSearchResponse> buses =
                busSearchTool.search(
                        details.getSource(),
                        details.getDestination()
                );

        return busSearchTool.format(
                buses,
                details.getSource(),
                details.getDestination()
        );
    }

    // =========================================================
    // CONTINUE MULTI-TURN FLOW
    // =========================================================

    private String continueFlow(
            ConversationMemory memory,
            String message) {

        switch (memory.getStep()) {

            case "WAITING_FOR_SOURCE":

                memory.setSource(
                        message.trim()
                );

                memory.setStep(
                        "WAITING_FOR_DESTINATION"
                );

                return "What is your destination?";

            case "WAITING_FOR_DESTINATION":

                memory.setDestination(
                        message.trim()
                );

                if ("BOOK_TICKET".equals(
                        memory.getCurrentIntent())) {

                    return searchForBooking(memory);
                }

                return null;

            case "WAITING_FOR_BUS_SELECTION":

                return handleBusSelection(
                        memory,
                        message
                );

            case "WAITING_FOR_DATE":

                memory.setTravelDate(
                        message.trim()
                );

                return afterDate(memory);

            case "WAITING_FOR_SEAT_COUNT":

                return handleSeatCount(
                        memory,
                        message
                );

            case "WAITING_FOR_SEAT_SELECTION":

                return handleSeatSelection(
                        memory,
                        message
                );

            case "WAITING_FOR_CONFIRMATION":

                return handleConfirmation(
                        memory,
                        message
                );

            case "WAITING_FOR_CANCEL_BOOKING_ID":

                try {

                    Long bookingId =
                            Long.parseLong(
                                    message.trim()
                            );

                    return cancelBooking(
                            memory,
                            bookingId
                    );

                } catch (NumberFormatException e) {

                    return "Please provide a valid Booking ID.";
                }

            case "WAITING_FOR_VIEW_BOOKING_ID":

                try {

                    Long bookingId =
                            Long.parseLong(
                                    message.trim()
                            );

                    return viewBooking(
                            memory,
                            bookingId
                    );

                } catch (NumberFormatException e) {

                    return "Please provide a valid Booking ID.";
                }

            default:
                return null;
        }
    }

    // =========================================================
    // BUS SELECTION
    // =========================================================

    private String handleBusSelection(
            ConversationMemory memory,
            String message) {

        List<BusSearchResponse> buses =
                memory.getBuses();

        try {

            int option =
                    Integer.parseInt(
                            message.trim()
                    );

            if (option < 1 ||
                    option > buses.size()) {

                return "Please select a valid bus option.";
            }

            BusSearchResponse selected =
                    buses.get(option - 1);

            memory.setSelectedBusIndex(option);

            memory.setSelectedBusId(
                    selected.getBusId()
            );

            return afterBusSelection(memory);

        } catch (NumberFormatException e) {

            /*
             * Also allow actual bus number.
             */

            for (int i = 0;
                 i < buses.size();
                 i++) {

                BusSearchResponse bus =
                        buses.get(i);

                if (bus.getBusNumber()
                        .equalsIgnoreCase(
                                message.trim())) {

                    memory.setSelectedBusIndex(
                            i + 1
                    );

                    memory.setSelectedBusId(
                            bus.getBusId()
                    );

                    return afterBusSelection(
                            memory
                    );
                }
            }

            return "Please reply with the bus option number.";
        }
    }

    private String afterBusSelection(
            ConversationMemory memory) {

        if (memory.getTravelDate() == null) {

            memory.setStep(
                    "WAITING_FOR_DATE"
            );

            return "What is your travel date? Example: 2026-09-20";
        }

        return afterDate(memory);
    }

    // =========================================================
    // DATE
    // =========================================================

    private String afterDate(
            ConversationMemory memory) {

        if (memory.getNumberOfSeats() == null) {

            memory.setStep(
                    "WAITING_FOR_SEAT_COUNT"
            );

            return "How many seats do you want?";
        }

        return showSeats(memory);
    }

    // =========================================================
    // SEAT COUNT
    // =========================================================

    private String handleSeatCount(
            ConversationMemory memory,
            String message) {

        try {

            int count =
                    Integer.parseInt(
                            message.trim()
                    );

            if (count <= 0) {

                return "Seat count must be greater than 0.";
            }

            memory.setNumberOfSeats(count);

            return showSeats(memory);

        } catch (NumberFormatException e) {

            return "Please enter a valid number. Example: 2";
        }
    }

    // =========================================================
    // SHOW SEATS
    // =========================================================

    private String showSeats(
            ConversationMemory memory) {

        List<SeatResponseDTO> seats =
                seatTool.getAvailableSeats(
                        memory.getSelectedBusId()
                );

        if (seats.isEmpty()) {

            memory.reset();

            return "Sorry, no seats are currently available.";
        }

        memory.setStep(
                "WAITING_FOR_SEAT_SELECTION"
        );

        return seatTool.format(seats);
    }

    // =========================================================
    // SEAT SELECTION
    // =========================================================

    private String handleSeatSelection(
            ConversationMemory memory,
            String message) {

        List<String> seatNumbers =
                Arrays.stream(
                        message.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isBlank())
                        .toList();

        if (seatNumbers.isEmpty()) {

            return "Please provide seat numbers.";
        }

        if (memory.getNumberOfSeats() != null &&
                seatNumbers.size() !=
                        memory.getNumberOfSeats()) {

            return "You requested "
                    + memory.getNumberOfSeats()
                    + " seat(s). Please select exactly "
                    + memory.getNumberOfSeats()
                    + ".";
        }

        List<SeatResponseDTO> availableSeats =
                seatTool.getAvailableSeats(
                        memory.getSelectedBusId()
                );

        List<Long> seatIds =
                availableSeats.stream()
                        .filter(seat ->
                                seatNumbers.stream()
                                        .anyMatch(number ->
                                                number.equalsIgnoreCase(
                                                        seat.getSeatNumber()
                                                )
                                        )
                        )
                        .map(SeatResponseDTO::getSeatId)
                        .toList();

        if (seatIds.size() != seatNumbers.size()) {

            return "One or more selected seats are unavailable. Please choose from the available seats.";
        }

        memory.setSelectedSeats(
                seatNumbers
        );

        memory.setSelectedSeatIds(
                seatIds
        );

        memory.setStep(
                "WAITING_FOR_CONFIRMATION"
        );

        return """
                📋 Please confirm your booking.

                Route: %s → %s
                Travel Date: %s
                Seats: %s

                Reply YES to confirm or NO to cancel.
                """
                .formatted(
                        memory.getSource(),
                        memory.getDestination(),
                        memory.getTravelDate(),
                        String.join(
                                ", ",
                                memory.getSelectedSeats()
                        )
                );
    }

    // =========================================================
    // CONFIRMATION
    // =========================================================

    private String handleConfirmation(
            ConversationMemory memory,
            String message) {

        if (message.equalsIgnoreCase("NO")) {

            memory.reset();

            return "Booking cancelled.";
        }

        if (!message.equalsIgnoreCase("YES")) {

            return "Please reply YES to confirm or NO to cancel.";
        }

        try {

            LocalDate travelDate =
                    LocalDate.parse(
                            memory.getTravelDate()
                    );

            BookingResponseDTO booking =
                    bookingTool.book(
                            memory.getUserId(),
                            memory.getSelectedBusId(),
                            memory.getSelectedSeatIds(),
                            travelDate
                    );

            Long bookingId =
                    booking.getBookingId();

            memory.reset();

            return """
                    ✅ Booking successful!

                    Booking ID: %s
                    """
                    .formatted(bookingId);

        } catch (Exception e) {

            return "Booking failed: " + e.getMessage();
        }
    }

    // =========================================================
    // CANCEL
    // =========================================================

    private String cancelBooking(
            ConversationMemory memory,
            Long bookingId) {

        try {

            String result =
                    cancellationTool.cancel(
                            bookingId
                    );

            memory.reset();

            return result;

        } catch (Exception e) {

            return "Unable to cancel booking: "
                    + e.getMessage();
        }
    }

    // =========================================================
    // VIEW BOOKING
    // =========================================================

    private String viewBooking(
            ConversationMemory memory,
            Long bookingId) {

        try {

            BookingResponseDTO booking =
                    viewBookingTool.getBooking(
                            bookingId
                    );

            memory.reset();

            return """
                    📋 Booking Details

                    Booking ID: %s
                    Travel Date: %s
                    Number of Seats: %s
                    Total Amount: ₹%s
                    Status: %s
                    """
                    .formatted(
                            booking.getBookingId(),
                            booking.getTravelDate(),
                            booking.getNumberOfSeats(),
                            booking.getTotalAmount(),
                            booking.getBookingStatus()
                    );

        } catch (Exception e) {

            return "Unable to find booking: "
                    + e.getMessage();
        }
    }

    // =========================================================
    // STATIC RESPONSES
    // =========================================================

    private String greeting() {

        return """
                👋 Welcome!

                I can help you with:

                1. Book Ticket
                2. Search Bus
                3. Cancel Ticket
                4. View Booking
                5. Seat Availability
                6. Payment Status

                Example:
                Book one seat from Chennai to Madurai
                """;
    }

    private String unknown() {

        return """
                Sorry, I couldn't understand.

                Try:
                • Book ticket from Chennai to Madurai
                • Search buses from Chennai to Madurai
                • Cancel booking
                • View booking
                """;
    }
}