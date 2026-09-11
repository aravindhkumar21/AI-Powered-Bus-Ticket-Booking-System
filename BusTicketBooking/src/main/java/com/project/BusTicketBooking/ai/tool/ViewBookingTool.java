package com.project.BusTicketBooking.ai.tool;

import org.springframework.stereotype.Component;

import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.service.BookingService;

@Component
public class ViewBookingTool {

    private final BookingService bookingService;

    public ViewBookingTool(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingResponseDTO getBooking(Long bookingId) {

        return bookingService.getBookingById(bookingId);
    }
}