package com.logic.springjwt.response;

import org.springframework.stereotype.Service;

@Service
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageResponse() {
    }
}
