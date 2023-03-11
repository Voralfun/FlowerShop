package com.example.flowershop.model.dto;

public class StripeResponseDTO {
    private String sessionId;

    public StripeResponseDTO(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
