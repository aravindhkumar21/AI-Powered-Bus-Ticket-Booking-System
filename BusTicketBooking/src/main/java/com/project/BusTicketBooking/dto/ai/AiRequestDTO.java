package com.project.BusTicketBooking.dto.ai;

public class AiRequestDTO {
	private String message;

    public AiRequestDTO() {
    }

    public AiRequestDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
