package com.project.BusTicketBooking.dto.booking;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class BookingUpdateDTO {

    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;

    public BookingUpdateDTO() {
    }

    public BookingUpdateDTO(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }
}