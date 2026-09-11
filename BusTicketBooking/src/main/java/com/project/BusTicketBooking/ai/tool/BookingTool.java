package com.project.BusTicketBooking.ai.tool;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.BusTicketBooking.dto.booking.BookingRequestDTO;
import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.service.BookingService;

@Component
public class BookingTool {

    private final BookingService bookingService;

    public BookingTool(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingResponseDTO book(
            Long userId,
            Long busId,
            List<Long> seatIds,
            LocalDate travelDate) {

        BookingRequestDTO request =
                new BookingRequestDTO();

        request.setUserId(userId);
        request.setBusId(busId);
        request.setSeatIds(seatIds);
        request.setTravelDate(travelDate);

        return bookingService.saveBooking(request);
    }
}