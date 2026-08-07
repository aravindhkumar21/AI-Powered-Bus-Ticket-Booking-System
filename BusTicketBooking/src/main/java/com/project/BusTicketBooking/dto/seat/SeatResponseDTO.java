package com.project.BusTicketBooking.dto.seat;

import com.project.BusTicketBooking.dto.bus.BusResponseDTO;
import com.project.BusTicketBooking.enums.SeatStatus;
import com.project.BusTicketBooking.enums.SeatType;

public class SeatResponseDTO {

    private Long seatId;

    private String seatNumber;

    private SeatType seatType;

    private SeatStatus seatStatus;

    private BusResponseDTO bus;

    public SeatResponseDTO() {
    }

    public SeatResponseDTO(Long seatId,
                           String seatNumber,
                           SeatType seatType,
                           SeatStatus seatStatus,
                           BusResponseDTO bus) {

        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.bus = bus;
    }

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
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

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public BusResponseDTO getBus() {
		return bus;
	}

	public void setBus(BusResponseDTO bus) {
		this.bus = bus;
	}

    
    // Generate Getters & Setters
}
