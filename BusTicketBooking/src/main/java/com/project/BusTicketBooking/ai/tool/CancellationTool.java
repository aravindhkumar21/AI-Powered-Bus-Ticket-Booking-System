package com.project.BusTicketBooking.ai.tool;

import org.springframework.stereotype.Component;

import com.project.BusTicketBooking.service.BookingService;

@Component
public class CancellationTool {

    private final BookingService bookingService;

    public CancellationTool(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public String cancel(Long bookingId) {

        bookingService.deleteBooking(bookingId);

        return "Booking " + bookingId +
                " cancelled successfully.";
    }
}