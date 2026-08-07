package com.project.BusTicketBooking.dto.seat;


import com.project.BusTicketBooking.enums.SeatType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class SeatRequestDTO {


    @NotBlank(message = "Seat number is required")
    private String seatNumber;


    @NotNull(message = "Seat type is required")
    private SeatType seatType;


    @NotNull(message = "Bus Id is required")
    private Long busId;



    public SeatRequestDTO() {
    }



    public SeatRequestDTO(String seatNumber,
            SeatType seatType,
            Long busId) {

        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.busId = busId;
    }



    public String getSeatNumber() {
        return seatNumber;
    }


    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }


    public SeatType getSeatType() {
        return seatType;
    }


    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }


    public Long getBusId() {
        return busId;
    }


    public void setBusId(Long busId) {
        this.busId = busId;
    }

}