package com.dto;

public class MessageDto {

    private final String message;

    public MessageDto(String content) {
        this.message = content;
    }

    public String getContent() {
        return message;
    }
}
