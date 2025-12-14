package com.example.Nucleus.dto.requestDto.commentRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommentRequestDto {
    @NotBlank(message = "Message is required.")
    private String message;
    @NotNull(message = "Task id is required.")
    private Long taskId;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String message, Long taskId) {
        this.message = message;
        this.taskId = taskId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
