package com.project.BusTicketBooking.dto.ai;

public class AiRequestDTO {

    private String sessionId;
    private Long userId;
    private String message;

    public AiRequestDTO() {
    }

    public AiRequestDTO(
            String sessionId,
            Long userId,
            String message) {

        this.sessionId = sessionId;
        this.userId = userId;
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}