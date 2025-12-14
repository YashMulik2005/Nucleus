package com.example.Nucleus.dto.requestDto.commentRequestDtos;

import jakarta.validation.constraints.NotBlank;

public class UpdateCommentRequestDto {
    @NotBlank(message = "Message is required.")
    private String message;

    public UpdateCommentRequestDto() {
    }

    public UpdateCommentRequestDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
